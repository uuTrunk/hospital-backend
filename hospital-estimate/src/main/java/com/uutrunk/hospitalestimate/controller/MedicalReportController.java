package com.uutrunk.hospitalestimate.controller;

import com.uutrunk.hospitalestimate.common.ApiResponse;
import com.uutrunk.hospitalestimate.service.MedicalReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/medical-report")
public class MedicalReportController {

    @Autowired
    private MedicalReportService medicalReportService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<Map<String, Object>>> uploadMedicalReport(
            @RequestParam("assessment_id") int assessmentId,
            @RequestParam("report_type") String reportType,
            @RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("成功");
        Map<String, Object> filePath = medicalReportService.uploadMedicalReport(assessmentId, reportType, file);
        return ResponseEntity.ok(ApiResponse.success(filePath));
    }
}
