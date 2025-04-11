package com.uutrunk.hospitalhealthdocument.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.nullness.qual.NonNull;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.uutrunk.hospitalhealthdocument.common.ApiResponse;
import com.uutrunk.hospitalhealthdocument.convertor.AdmissionHistoryConvertor;
import com.uutrunk.hospitalhealthdocument.convertor.DiagnosisPlanConvertor;
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
    
    @Override
    public Page<HealthRecordDTO> listHealthRecords(HealthRecordQueryDTO queryDTO) {
        Page<HealthRecordMain> page = new Page<>(queryDTO.getPage(), queryDTO.getPageSize());
        
        QueryWrapper<HealthRecordMain> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(queryDTO.getPatientName())) {
            wrapper.like("patient_info.name", queryDTO.getPatientName());
        }
        if (StringUtils.isNotBlank(queryDTO.getRecordStatus())) {
            wrapper.eq("status", queryDTO.getRecordStatus());
        }
        
        // 执行分页查询（需要Mapper有联表查询实现）
        Page<HealthRecordMain> result = healthRecordMainMapper.selectWithPatient(page, wrapper);
        
        // 转换DTO
        List<HealthRecordDTO> dtos = result.getRecords().stream()
            .map(HealthRecordDTO::fromEntity)
            .collect(Collectors.toList());
        
        Page<HealthRecordDTO> dtoPage = new Page<>(page.getCurrent(), page.getSize(), result.getTotal());
        dtoPage.setRecords(dtos);
        
        return dtoPage;
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