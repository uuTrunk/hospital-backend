package com.uutrunk.hospitalestimate.Controller;

import com.uutrunk.hospitalestimate.Service.MedicalReportService;
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
    public ResponseEntity<Map<String, Object>> uploadMedicalReport(
            @RequestParam("assessment_id") int assessmentId,
            @RequestParam("report_type") String reportType,
            @RequestParam("file") MultipartFile file) {
        try {
            Map<String, Object> filePath = medicalReportService.uploadMedicalReport(assessmentId, reportType, file);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 200);
            response.put("message", "文件上传成功");
            response.put("data", filePath);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", 500);
            errorResponse.put("message", "文件上传失败: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
