package com.julien.hospitaldischargeservice.controller;

import com.julien.hospitaldischargeservice.dto.DischargeSummaryDTO;
import com.julien.hospitaldischargeservice.entity.DischargeSummary;
import com.julien.hospitaldischargeservice.service.DischargeSummaryService;
import com.julien.hospitaldischargeservice.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/discharge/summary")
public class DischargeSummaryController {

    private final DischargeSummaryService dischargeSummaryService;

    /**
     * 获取离院小结详情
     */
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<DischargeSummaryDTO>> getDischargeSummaryDetail(
            @RequestParam("discharge_id") Integer dischargeId) {

        DischargeSummary dischargeSummary = dischargeSummaryService.getDischargeSummaryByDischargeId(dischargeId);

        if (dischargeSummary == null) {
            return ResponseEntity.status(404).body(new ApiResponse<>(404, "未找到离院小结", null));
        }

        // 将实体转换为 DTO
        DischargeSummaryDTO dto = convertToDto(dischargeSummary);

        return ResponseEntity.ok(new ApiResponse<>(200, "成功", dto));
    }

    /**
     * 提交离院小结
     */
    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<String>> submitDischargeSummary(
            @RequestBody DischargeSummary dischargeSummary) {

        // 校验离院记录是否存在，可以在服务层实现
        if (dischargeSummary.getDischargeMain() == null || dischargeSummary.getDischargeMain().getDischargeId() == null) {
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

    // 将实体转换为 DTO
    private DischargeSummaryDTO convertToDto(DischargeSummary dischargeSummary) {
        DischargeSummaryDTO dto = new DischargeSummaryDTO();
        dto.setSummaryType(String.valueOf(dischargeSummary.getSummaryType()));
        dto.setAdmissionDiagnosis(dischargeSummary.getAdmissionDiagnosis());
        dto.setInHospitalCondition(dischargeSummary.getInHospitalCondition());
        dto.setTreatmentProcess(dischargeSummary.getTreatmentProcess());
        dto.setDischargeCondition(dischargeSummary.getDischargeCondition());

        if ("死亡小结".equals(dischargeSummary.getSummaryType())) {
            dto.setRescueProcess(dischargeSummary.getRescueProcess());
            dto.setDeathCause(dischargeSummary.getDeathCause());
        }

        return dto;
    }
}
