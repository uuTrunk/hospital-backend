package com.uutrunk.hospitalestimate.Controller;

import com.uutrunk.hospitalestimate.Service.CareAssessmentService;
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
    public ResponseEntity<Map<String, Object>> getCareAssessmentDetail(
            @RequestParam("assessment_id") int assessmentId) {
        try {
            Map<String, Object> detail = careAssessmentService.getCareAssessmentDetail(assessmentId);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "成功");
            response.put("data", detail);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", 500);
            errorResponse.put("message", "获取照护评估详情失败: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitCareAssessment(
            @RequestParam("assessment_id") int assessmentId,
            @RequestBody List<Map<String, String>> assessmentItems) {
        try {
            careAssessmentService.submitCareAssessment(assessmentId, assessmentItems);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "照护评估提交成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", 500);
            errorResponse.put("message", "照护评估提交失败: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
