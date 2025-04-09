package com.uutrunk.hospitalestimate.controller;

import com.uutrunk.hospitalestimate.common.ApiResponse;
import com.uutrunk.hospitalestimate.service.CareAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/care-assessment")
public class CareAssessmentController {

    @Autowired
    private CareAssessmentService careAssessmentService;

    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getCareAssessmentDetail(
            @RequestParam("assessment_id") int assessmentId) {
        Map<String, Object> detail = careAssessmentService.getCareAssessmentDetail(assessmentId);
        return ResponseEntity.ok(ApiResponse.success(detail));
    }

    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<Void>> submitCareAssessment(
            @RequestParam("assessment_id") int assessmentId,
            @RequestBody List<Map<String, String>> assessmentItems) {
        careAssessmentService.submitCareAssessment(assessmentId, assessmentItems);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
