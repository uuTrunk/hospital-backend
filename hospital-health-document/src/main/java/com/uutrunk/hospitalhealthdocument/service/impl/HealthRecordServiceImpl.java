package com.uutrunk.hospitalhealthdocument.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.nullness.qual.NonNull;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.uutrunk.hospitalhealthdocument.common.ApiResponse;
import com.uutrunk.hospitalhealthdocument.convertor.AdmissionHistoryConvertor;
import com.uutrunk.hospitalhealthdocument.convertor.DiagnosisPlanConvertor;
import com.uutrunk.hospitalhealthdocument.convertor.HealthRecordConvertor;
import com.uutrunk.hospitalhealthdocument.dto.*;
import com.uutrunk.hospitalhealthdocument.exception.DatabaseException;
import com.uutrunk.hospitalhealthdocument.mapper.*;
import com.uutrunk.hospitalhealthdocument.pojo.*;
import com.uutrunk.hospitalhealthdocument.service.HealthRecordService;
import jakarta.persistence.EntityNotFoundException;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 新增文件：健康档案业务实现

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
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
    public String createHealthRecord(HealthRecordCreateDTO createDTO) {
        // 参数校验
        if (createDTO.getPatientId() == null) {
            throw new IllegalArgumentException("患者ID不能为空");
        }
//        System.out.println(createDTO.getPatientId());
        // 检查患者是否存在
        PatientInfo patient = patientMapper.selectById(createDTO.getPatientId());
//        System.out.println("查找患者后");
        // 生成格式化 ID
        String recordId = UUID.randomUUID().toString().replace("-", "");

        // 创建主表记录
        HealthRecordMain main = new HealthRecordMain();
        main.setRecordId(recordId);
        main.setPatientId(createDTO.getPatientId());
        main.setCreateDoctorName(createDTO.getCreateDoctorName());
        main.setCreateTime(LocalDateTime.now());
        main.setUpdateTime(LocalDateTime.now());
        main.setStatus(HealthRecordMain.Status.待完善);
        healthRecordMainMapper.insert(main);

        HealthRecordContentDTO content = createDTO.getBasicInfo();

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
        
        return recordId;
    }
    
    @Override
    public HealthRecordDetailDTO getDetail(String recordId) {
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
        detail.setPatientInfo(PatientDTO.fromEntity(patient));
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

        // 转换DTO（确保selectWithPatient已包含patient_info关联数据）
        List<HealthRecordDTO> dtoList = recordPage.getRecords().stream()
                .map(main -> {
                    HealthRecordDTO dto = HealthRecordConvertor.INSTANCE.toDTO(main);
                    // 从联表结果中获取patient_info信息（需确保实体关联）
                    PatientInfo patient = patientMapper.selectById(main.getPatientId()); // 假设实体有patient属性
                    if (patient != null) {
                        dto.setPatientName(patient.getName());
                    }
                    return dto;
                })
                .collect(Collectors.toList());

        // 构建分页响应
        PageResult<HealthRecordDTO> result = new PageResult<>();
        result.setTotal(recordPage.getTotal());
        result.setList(dtoList);
        return result;
    }


    @Transactional
    @Override
    public void updateHealthRecord(String recordId, Map<String, Object> updateContent) {
        // 校验档案是否存在
        HealthRecordMain main = healthRecordMainMapper.selectById(recordId);
        if (main == null) {
            throw new EntityNotFoundException("健康档案不存在");
        }
        
        // 更新主表字段
        if (updateContent.containsKey("status")) {
            main.setStatus((HealthRecordMain.Status)updateContent.get("status"));
            healthRecordMainMapper.updateById(main);
        }

        if(updateContent.containsKey("patientId")) {
            main.setPatientId((Integer) updateContent.get("patientId"));
            healthRecordMainMapper.updateById(main);
        }
        if(updateContent.containsKey("createdDoctorId")) {
            main.setCreateDoctorName((String) updateContent.get("createdDoctorName"));
            healthRecordMainMapper.updateById(main);
        }
        if(updateContent.containsKey("createTime")) {
            main.setCreateTime((LocalDateTime) updateContent.get("createTime"));
            healthRecordMainMapper.updateById(main);
        }
        if(updateContent.containsKey("updateTime")) {
            main.setUpdateTime((LocalDateTime) updateContent.get("updateTime"));
            healthRecordMainMapper.updateById(main);
        }
        
        return;
    }
    
    @Transactional
    @Override
    public void addHistory(AdmissionHistoryCreateDTO historyDTO) {
        AdmissionHistory history = new AdmissionHistory();
        history.setRecordId(historyDTO.getRecordId());
        history.setTypeId(historyDTO.getTypeId());
        history.setContent(historyDTO.getContent());
        admissionHistoryMapper.insert(history);
        return;
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
}