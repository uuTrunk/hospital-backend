package com.uutrunk.hospitalhealthdocument.service;

import com.alibaba.nacos.shaded.org.checkerframework.checker.nullness.qual.NonNull;
import com.uutrunk.hospitalhealthdocument.common.ApiResponse;
import com.uutrunk.hospitalhealthdocument.dto.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

// 新增文件：健康档案业务接口
public interface HealthRecordService {
    // 健康档案列表查询
    ApiResponse<Page<HealthRecordDTO>> listHealthRecords(HealthRecordQueryDTO queryDTO);
    
    // 健康档案详情查询
    ApiResponse<HealthRecordDetailDTO> getDetail(String recordId);
    
    // 创建健康档案
    ApiResponse<String> createHealthRecord(HealthRecordCreateDTO createDTO);
    
    // 更新健康档案
    ApiResponse<Void> updateHealthRecord(String recordId, Map<String, Object> updateContent);
    
    // 病史管理接口
    ApiResponse<Void> addHistory(AdmissionHistoryCreateDTO historyDTO);
    ApiResponse<Void> updateHistory(AdmissionHistoryUpdateDTO historyDTO);
    ApiResponse<Void> deleteHistory(@NonNull Integer historyId);
}