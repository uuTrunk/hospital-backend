// 新增文件：健康档案控制器
package com.uutrunk.hospitalhealthdocument.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uutrunk.hospitalhealthdocument.common.ApiResponse;
import com.uutrunk.hospitalhealthdocument.dto.*;
import com.uutrunk.hospitalhealthdocument.service.HealthRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/health-record")
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;

    // 健康档案列表查询接口
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PageResult<HealthRecordDTO>>> listHealthRecords(
        @RequestBody HealthRecordQueryDTO queryDTO) {
        return ResponseEntity.ok(ApiResponse.success(healthRecordService.listHealthRecords(queryDTO)));
    }

    // 健康档案详情查询接口
    @GetMapping("/detail/{recordId}")
    public ResponseEntity<ApiResponse<HealthRecordDetailDTO>> getDetail(
        @PathVariable String recordId) {
        return ResponseEntity.ok(ApiResponse.success(healthRecordService.getDetail(recordId)));
    }

    // 创建健康档案接口
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> createHealthRecord(
        @RequestBody HealthRecordCreateDTO createDTO) {
        return ResponseEntity.ok(ApiResponse.success(healthRecordService.createHealthRecord(createDTO)));
    }

    // 更新健康档案接口
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Void>> updateHealthRecord(
        @RequestParam String recordId,
        @RequestBody Map<String, Object> updateContent) {
        healthRecordService.updateHealthRecord(recordId, updateContent);
        return ResponseEntity.ok(ApiResponse.success());
    }

    // 病史管理接口
    @PostMapping("/history")
    public ResponseEntity<ApiResponse<Void>> addHistory(
        @RequestBody AdmissionHistoryCreateDTO historyDTO) {
        return ResponseEntity.ok(ApiResponse.success());
    }

    @PutMapping("/history")
    public ResponseEntity<ApiResponse<Void>> updateHistory(
        @RequestBody AdmissionHistoryUpdateDTO historyDTO) {
        return ResponseEntity.ok(ApiResponse.success());
    }

    @DeleteMapping("/history/{historyId}")
    public ResponseEntity<ApiResponse<Void>> deleteHistory(
        @PathVariable @NonNull Integer historyId) {
        return ResponseEntity.ok(ApiResponse.success());
    }
}