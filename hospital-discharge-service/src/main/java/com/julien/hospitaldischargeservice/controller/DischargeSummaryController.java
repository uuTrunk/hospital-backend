package com.julien.hospitaldischargeservice.controller;

import com.julien.hospitaldischargeservice.entity.DischargeSummary;
import com.julien.hospitaldischargeservice.service.DischargeSummaryService;
import com.julien.hospitaldischargeservice.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discharge/summary")
@RequiredArgsConstructor
public class DischargeSummaryController {

    private final DischargeSummaryService dischargeSummaryService;

    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<DischargeSummary>> getDischargeSummaryDetail(
            @RequestParam("discharge_id") Integer dischargeId) {

        DischargeSummary dischargeSummary = dischargeSummaryService.getDischargeSummaryByDischargeId(dischargeId);

        if (dischargeSummary == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>(404, "未找到离院小结", null));
        }

        return ResponseEntity.ok(new ApiResponse<>(200, "成功", dischargeSummary));
    }
}
