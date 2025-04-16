package com.uutrunk.hospitalhealthdocument.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.nullness.qual.NonNull;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.uutrunk.hospitalhealthdocument.common.ApiResponse;
import com.uutrunk.hospitalhealthdocument.convertor.AdmissionHistoryConvertor;
import com.uutrunk.hospitalhealthdocument.convertor.DiagnosisPlanConvertor;
import com.uutrunk.hospitalhealthdocument.convertor.HealthRecordConvertor;
import com.uutrunk.hospitalhealthdocument.convertor.PatientConvertor;
import com.uutrunk.hospitalhealthdocument.dto.*;
import com.uutrunk.hospitalhealthdocument.exception.DatabaseException;
import com.uutrunk.hospitalhealthdocument.mapper.*;
import com.uutrunk.hospitalhealthdocument.pojo.*;
import com.uutrunk.hospitalhealthdocument.service.HealthRecordService;
import jakarta.persistence.EntityNotFoundException;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 新增文件：健康档案业务实现

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.alibaba.nacos.client.naming.core.Balancer.RandomByWeight.selectAll;

@DubboService
public class HealthRecordServiceImpl implements HealthRecordService {
    @Autowired
    private HealthRecordMainMapper healthRecordMainMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private AdmissionHistoryMapper admissionHistoryMapper;
    @Autowired
    private DiagnosisPlanMapper diagnosisPlanMapper;
    
    @Transactional
    @Override
    public HealthRecordResponseDTO createHealthRecord(HealthRecordCreateDTO createDTO) {
        // 参数校验
        if (createDTO.getPatientId() == null) {
            throw new IllegalArgumentException("患者ID不能为空");
        }
//        System.out.println(createDTO.getPatientId());
        // 检查患者是否存在
        PatientInfo patient = patientMapper.selectById(createDTO.getPatientId());
//        System.out.println("查找患者后");

        // 创建主表记录
        HealthRecordMain main = new HealthRecordMain();
        main.setPatientId(createDTO.getPatientId());
        main.setCreateDoctorName(createDTO.getCreateDoctorName());
        main.setCreateTime(LocalDateTime.now());
        main.setUpdateTime(LocalDateTime.now());
        main.setStatus(HealthRecordMain.Status.待完善);
        healthRecordMainMapper.insert(main);

        HealthRecordContentDTO content = createDTO.getBasicInfo();

        Integer recordId = main.getRecordId();
        System.out.println(recordId);

        //创建入院病史表
        AdmissionHistoryDTO historyDTO = content.getAdmissionHistoryDTO();
        AdmissionHistory history = AdmissionHistoryConvertor.INSTANCE.toEntity(historyDTO);
        history.setRecordId(recordId);
        admissionHistoryMapper.insert(history);

        //创建诊断与计划表
        DiagnosisPlanDTO planDTO = content.getDiagnosisPlanDTO();
        DiagnosisPlan plan = DiagnosisPlanConvertor.INSTANCE.toEntity(planDTO);
        plan.setRecordId(recordId);
        diagnosisPlanMapper.insert(plan);

        HealthRecordResponseDTO response = new HealthRecordResponseDTO();
        response.setRecordId(recordId);
        
        return response;
    }
    
    @Override
    public HealthRecordDetailDTO getDetail(Integer recordId) {
        // 查询主表信息
        HealthRecordMain main = healthRecordMainMapper.selectById(recordId);
        if (main == null) {
            throw new EntityNotFoundException("健康档案不存在");
        }
        
        // 查询患者信息
        PatientInfo patient = patientMapper.selectById(main.getPatientId());
        
        // 查询病史记录
        QueryWrapper<AdmissionHistory> historyWrapper = new QueryWrapper<>();
        historyWrapper.eq("record_id", recordId);
        List<AdmissionHistory> histories = admissionHistoryMapper.selectList(historyWrapper);
        
        // 查询诊断计划
        QueryWrapper<DiagnosisPlan> planWrapper = new QueryWrapper<>();
        planWrapper.eq("record_id", recordId);
        List<DiagnosisPlan> plans = diagnosisPlanMapper.selectList(planWrapper);
        
        // 组装DTO
        HealthRecordDetailDTO detail = new HealthRecordDetailDTO();
        detail.setRecordId(recordId);
        detail.setPatientInfo(PatientConvertor.INSTANCE.toDTO(patient));
        detail.setHistoryList(histories.stream()
            .map(AdmissionHistoryDTO::fromEntity)
            .collect(Collectors.toList()));
        detail.setDiagnosisList(plans.stream()
            .map(DiagnosisPlanDTO::fromEntity)
            .collect(Collectors.toList()));
        
        return detail;
    }

    // 在HealthRecordServiceImpl中修改listHealthRecords方法
    @Override
    public PageResult<HealthRecordDTO> listHealthRecords(HealthRecordQueryDTO queryDTO) {
        // 创建分页对象
        Page<HealthRecordMain> page = new Page<>(queryDTO.getPage(), queryDTO.getPageSize());

        QueryWrapper<PatientInfo> patientWrapper = new QueryWrapper<>();
        patientWrapper.like("patient_info.name", queryDTO.getPatientName());
        List<Integer> patientIds = patientMapper.selectList(patientWrapper).stream()
                .map(PatientInfo::getPatientId)
                .collect(Collectors.toList());
        // 构建查询条件
        QueryWrapper<HealthRecordMain> wrapper = new QueryWrapper<>();
        wrapper.in("patient_id", patientIds);
        if (queryDTO.getRecordStatus() != null && !queryDTO.getRecordStatus().isEmpty()) {
            wrapper.eq("status", queryDTO.getRecordStatus());
        }

        // 执行联表分页查询
        IPage<HealthRecordMain> recordPage = healthRecordMainMapper.selectPage(page, wrapper);
        PatientDetailDTO patientDetailDTO = PatientConvertor.INSTANCE.toDetailDTO(patientMapper.selectOne(patientWrapper));

        // 转换DTO（确保selectWithPatient已包含patient_info关联数据）
        List<HealthRecordDTO> dtoList = recordPage.getRecords().stream()
                .map(main -> {
                    HealthRecordDTO dto = HealthRecordConvertor.INSTANCE.toDTO(main);
                    // 从联表结果中获取patient_info信息（需确保实体关联）
                    PatientInfo patient = patientMapper.selectById(main.getPatientId()); // 假设实体有patient属性
                    if (patient != null) {
                        dto.setPatientName(patient.getName());
                    }
                    dto.setPatientDetailDTO(patientDetailDTO);
                    return dto;
                })
                .collect(Collectors.toList());

        // 构建分页响应
        PageResult<HealthRecordDTO> result = new PageResult<>();
        result.setTotal(recordPage.getTotal());
        result.setList(dtoList);
        return result;
    }

    @Override
    public List<HealthRecordDTO> listAllHealthRecords() {
        List<HealthRecordMain> records = healthRecordMainMapper.selectAll();
        List<HealthRecordDTO> list = new ArrayList<>();
        for(HealthRecordMain record : records) {
            HealthRecordDTO dto = HealthRecordConvertor.INSTANCE.toDTO(record);
            list.add(dto);
        }
        return list;
    }


    @Transactional
    @Override
    public void updateHealthRecord(Integer recordId, HealthRecordUpdateDTO updateContent) {
        // 校验档案是否存在
        HealthRecordMain main = healthRecordMainMapper.selectById(recordId);
        if (main == null) {
            throw new EntityNotFoundException("健康档案不存在");
        }

        HealthRecordDTO healthRecordDTO = updateContent.getHealthRecordDTO();
        HealthRecordContentDTO content = updateContent.getHealthRecordContentDTO();
        AdmissionHistoryDTO historyDTO = content.getAdmissionHistoryDTO();
        DiagnosisPlanDTO planDTO = content.getDiagnosisPlanDTO();
        
        // 更新时间改变
        main.setUpdateTime(LocalDateTime.now());
        healthRecordMainMapper.updateById(main);

        //更新健康档案主表
        main = HealthRecordConvertor.INSTANCE.toEntity(healthRecordDTO);
        healthRecordMainMapper.updateById(main);

        //更新入院病历表
        QueryWrapper<AdmissionHistory> admissionHistoryWrapper = new QueryWrapper<>();
        admissionHistoryWrapper.eq("record_id", recordId);
        AdmissionHistory admissionHistory = admissionHistoryMapper.selectOne(admissionHistoryWrapper);
        admissionHistory = AdmissionHistoryConvertor.INSTANCE.toEntity(historyDTO);
        admissionHistoryMapper.updateById(admissionHistory);

        //更新诊断与计划表
        QueryWrapper<DiagnosisPlan> diagnosisPlanWrapper = new QueryWrapper<>();
        diagnosisPlanWrapper.eq("record_id", recordId);
        DiagnosisPlan diagnosisPlan = diagnosisPlanMapper.selectOne(diagnosisPlanWrapper);
        diagnosisPlan = DiagnosisPlanConvertor.INSTANCE.toEntity(planDTO);
        diagnosisPlanMapper.updateById(diagnosisPlan);
        
        return;
    }
    
    @Transactional
    @Override
    public AdmissionHistoryResponseDTO addHistory(AdmissionHistoryCreateDTO historyDTO) {
        AdmissionHistory history = new AdmissionHistory();
        history.setRecordId(historyDTO.getRecordId());
        history.setHistoryType(historyDTO.getHistoryType());
        history.setContent(historyDTO.getContent());
        admissionHistoryMapper.insert(history);
        AdmissionHistoryResponseDTO response = new AdmissionHistoryResponseDTO();
        response.setHistoryId(history.getHistoryId());
        return response;
    }
    
    @Transactional
    @Override
    public void updateHistory(AdmissionHistoryUpdateDTO historyDTO) {
        AdmissionHistory history = admissionHistoryMapper.selectById(historyDTO.getHistoryId());
        if (history == null) {
            throw new EntityNotFoundException("病史记录不存在");
        }
        history.setContent(historyDTO.getContent());
        admissionHistoryMapper.updateById(history);
        return;
    }
    
    @Transactional
    @Override
    public void deleteHistory(@NonNull Integer historyId) {
        AdmissionHistory history = admissionHistoryMapper.selectById(historyId);
        if (history == null) {
            throw new EntityNotFoundException("病史记录不存在");
        }
        admissionHistoryMapper.deleteById(historyId);
        return;
    }

    /**
     * @param patientId
     * @return
     */
    @Override
    public HealthRecordDetailDTO getDetailByPatientId(Integer patientId) {
        HealthRecordMain record = healthRecordMainMapper.selectOne(new QueryWrapper<HealthRecordMain>().eq("patient_id", patientId));
        return getDetail(record.getRecordId());
    }
}