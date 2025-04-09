package com.uutrunk.hospitalestimate.controller;

import com.uutrunk.hospitalestimate.common.ApiResponse;
import com.uutrunk.hospitalestimate.service.HealthAssessmentService;
import com.uutrunk.hospitalestimate.vo.HealthAssessmentVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthAssessmentController {
    private final HealthAssessmentService healthAssessmentService;

    public HealthAssessmentController(HealthAssessmentService healthAssessmentService) {
        this.healthAssessmentService = healthAssessmentService;
    }

    @GetMapping("/api/health-assessment/detail")
    public ApiResponse<HealthAssessmentVO> getDetail(int assessmentId) {
        HealthAssessmentVO detail = healthAssessmentService.getDetail(assessmentId);
        return ApiResponse.success(detail);
    }

    @PostMapping("/api/health-assessment/submit")
    public ApiResponse<Void> submit(HealthAssessmentVO healthAssessmentVO) {
        healthAssessmentService.submit(healthAssessmentVO);
        return ApiResponse.success(null);
    }
}
