package com.julien.hospitaldischargeservice.controller;

import com.julien.hospitaldischargeservice.entity.PatientInfo;
import com.julien.hospitaldischargeservice.service.PatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/patient-info")
@CrossOrigin(origins = "*")
public class PatientInfoController {

    @Autowired
    private PatientInfoService patientInfoService;

    @GetMapping("/detail")
    public ResponseEntity<Map<String, Object>> getPatientInfoByAssessmentId(
            @RequestParam("discharge_id") Integer dischargeId) {
        PatientInfo patientInfo = patientInfoService.getPatientInfoByDischargeId(dischargeId);

        if (patientInfo == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "成功");
        response.put("data", patientInfo);

        return ResponseEntity.ok(response);
    }
} 