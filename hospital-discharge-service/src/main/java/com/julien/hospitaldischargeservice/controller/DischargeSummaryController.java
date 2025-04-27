package com.julien.hospitaldischargeservice.controller;

import com.julien.hospitaldischargeservice.entity.DischargeSummary;
import com.julien.hospitaldischargeservice.service.DischargeSummaryService;
import com.julien.hospitaldischargeservice.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discharge/summary")
public class DischargeSummaryController {

    @Autowired
    private DischargeSummaryService dischargeSummaryService;

    /**
     * 获取离院小结详情
     */
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<DischargeSummary>> getDischargeSummaryDetail(
            @RequestParam("discharge_id") Integer dischargeId) {

        DischargeSummary dischargeSummary = dischargeSummaryService.getDischargeSummaryByDischargeId(dischargeId);

        if (dischargeSummary == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>(404, "未找到离院小结", null));
        }

        return ResponseEntity.ok(new ApiResponse<>(200, "成功", dischargeSummary));
    }

    /**
     * 提交离院小结
     */
    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<String>> submitDischargeSummary(
            @RequestBody DischargeSummary dischargeSummary) {

        // 校验离院记录是否存在，可以在服务层实现
        if (dischargeSummary.getDischargeMain() == null
                || dischargeSummary.getDischargeMain().getDischargeId() == null) {
            return ResponseEntity.status(400).body(new ApiResponse<>(400, "离院记录 ID 必须提供", null));
        }

        // 保存离院小结
        DischargeSummary savedSummary = dischargeSummaryService.saveDischargeSummary(dischargeSummary);

        if (savedSummary != null) {
            return ResponseEntity.ok(new ApiResponse<>(200, "离院小结提交成功", "提交成功"));
        } else {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "提交失败", null));
        }
    }
}
