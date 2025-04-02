package com.uutrunk.hospitalestimate.Controller;

import com.uutrunk.hospitalestimate.Service.HealthAssessmentService;
import com.uutrunk.hospitalestimate.VO.HealthAssessmentVO;
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
    public HealthAssessmentVO getDetail(int assessmentId) {
        return healthAssessmentService.getDetail(assessmentId);
    }

    @PostMapping("/api/health-assessment/submit")
    public void submit(HealthAssessmentVO healthAssessmentVO) {
        healthAssessmentService.submit(healthAssessmentVO);
    }
}
