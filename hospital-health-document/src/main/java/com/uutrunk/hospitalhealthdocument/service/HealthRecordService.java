package com.uutrunk.hospitalhealthdocument.service;

import com.alibaba.nacos.shaded.org.checkerframework.checker.nullness.qual.NonNull;
import com.uutrunk.hospitalhealthdocument.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

// 新增文件：健康档案业务接口
public interface HealthRecordService {
    // 健康档案列表查询
    PageResult<HealthRecordDTO> listHealthRecords(HealthRecordQueryDTO queryDTO);

    List<HealthRecordDTO> listAllHealthRecords();
    
    // 健康档案详情查询
    HealthRecordDetailDTO getDetail(Integer recordId);
    
    // 创建健康档案
    HealthRecordResponseDTO createHealthRecord(HealthRecordCreateDTO createDTO);
    
    // 更新健康档案
    void updateHealthRecord(Integer recordId, HealthRecordUpdateDTO updateContent);
    
    // 病史管理接口
    AdmissionHistoryResponseDTO addHistory(AdmissionHistoryCreateDTO historyDTO);
    void updateHistory(AdmissionHistoryUpdateDTO historyDTO);
    void deleteHistory(@NonNull Integer historyId);

    HealthRecordDetailDTO getDetailByPatientId(Integer patientId);
}