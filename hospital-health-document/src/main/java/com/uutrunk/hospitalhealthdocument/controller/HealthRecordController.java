// 新增文件：健康档案控制器
package com.uutrunk.hospitalhealthdocument.controller;

import com.uutrunk.hospitalhealthdocument.common.ApiResponse;
import com.uutrunk.hospitalhealthdocument.dto.*;
import com.uutrunk.hospitalhealthdocument.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list/all")
    public ResponseEntity<ApiResponse<List<HealthRecordDTO>>> listAllHealthRecords() {
        return ResponseEntity.ok(ApiResponse.success(healthRecordService.listAllHealthRecords()));
    }

    // 健康档案详情查询接口
    @GetMapping("/detail/{recordId}")
    public ResponseEntity<ApiResponse<HealthRecordDetailDTO>> getDetail(
        @PathVariable Integer recordId) {
        return ResponseEntity.ok(ApiResponse.success(healthRecordService.getDetail(recordId)));
    }

    // 创建健康档案接口
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<HealthRecordResponseDTO>> createHealthRecord(
        @RequestBody HealthRecordCreateDTO createDTO) {
        return ResponseEntity.ok(ApiResponse.success(healthRecordService.createHealthRecord(createDTO)));
    }

    // 更新健康档案接口
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Void>> updateHealthRecord(
        @RequestParam Integer recordId,
        @RequestBody HealthRecordUpdateDTO updateContent) {
        healthRecordService.updateHealthRecord(recordId, updateContent);
        return ResponseEntity.ok(ApiResponse.success());
    }

    // 病史管理接口
    @PostMapping("/history")
    public ResponseEntity<ApiResponse<AdmissionHistoryResponseDTO>> addHistory(
        @RequestBody AdmissionHistoryCreateDTO historyDTO) {
        return ResponseEntity.ok(ApiResponse.success(healthRecordService.addHistory(historyDTO)));
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