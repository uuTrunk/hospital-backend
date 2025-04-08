// 新增文件：健康档案控制器
package com.uutrunk.hospitalhealthdocument.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uutrunk.hospitalhealthdocument.common.ApiResponse;
import com.uutrunk.hospitalhealthdocument.dto.*;
import com.uutrunk.hospitalhealthdocument.service.HealthRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/health-record")
@Validated
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;

    // 健康档案列表查询接口
    @GetMapping("/list")
    public ApiResponse<Page<HealthRecordDTO>> listHealthRecords(
        @Valid @ModelAttribute HealthRecordQueryDTO queryDTO) {
        return healthRecordService.listHealthRecords(queryDTO);
    }

    // 健康档案详情查询接口
    @GetMapping("/detail/{recordId}")
    public ApiResponse<HealthRecordDetailDTO> getDetail(
        @PathVariable String recordId) {
        return healthRecordService.getDetail(recordId);
    }

    // 创建健康档案接口
    @PostMapping("/create")
    public ApiResponse<String> createHealthRecord(
        @Valid @RequestBody HealthRecordCreateDTO createDTO) {
        return healthRecordService.createHealthRecord(createDTO);
    }

    // 更新健康档案接口
    @PutMapping("/update")
    public ApiResponse<Void> updateHealthRecord(
        @RequestParam String recordId,
        @RequestBody Map<String, Object> updateContent) {
        return healthRecordService.updateHealthRecord(recordId, updateContent);
    }

    // 病史管理接口
    @PostMapping("/history")
    public ApiResponse<Void> addHistory(
        @Valid @RequestBody AdmissionHistoryCreateDTO historyDTO) {
        return healthRecordService.addHistory(historyDTO);
    }

    @PutMapping("/history")
    public ApiResponse<Void> updateHistory(
        @Valid @RequestBody AdmissionHistoryUpdateDTO historyDTO) {
        return healthRecordService.updateHistory(historyDTO);
    }

    @DeleteMapping("/history/{historyId}")
    public ApiResponse<Void> deleteHistory(
        @PathVariable @NonNull Integer historyId) {
        return healthRecordService.deleteHistory(historyId);
    }
}