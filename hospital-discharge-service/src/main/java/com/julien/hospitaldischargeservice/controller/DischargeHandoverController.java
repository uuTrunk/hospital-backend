package com.julien.hospitaldischargeservice.controller;

import com.julien.hospitaldischargeservice.entity.DischargeHandover;
import com.julien.hospitaldischargeservice.service.DischargeHandoverService;
import com.julien.hospitaldischargeservice.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discharge/handover")
@RequiredArgsConstructor
public class DischargeHandoverController {

    private final DischargeHandoverService dischargeHandoverService;

    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<String>> submitDischargeHandover(
            @RequestParam("discharge_id") Integer dischargeId,
            @RequestBody List<DischargeHandover> handoverItems) {

        boolean isSubmitted = dischargeHandoverService.submitDischargeHandover(dischargeId, handoverItems);

        if (isSubmitted) {
            return ResponseEntity.ok(new ApiResponse<>(200, "离院交接提交成功", null));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse<>(400, "提交离院交接失败", null));
        }
    }
}
