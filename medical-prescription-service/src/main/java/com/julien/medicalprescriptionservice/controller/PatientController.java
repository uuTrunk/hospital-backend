package com.julien.medicalprescriptionservice.controller;

import com.julien.medicalprescriptionservice.dto.PatientListRequest;
import com.julien.medicalprescriptionservice.dto.PatientListResponse;
import com.julien.medicalprescriptionservice.service.PatientService;
import com.julien.medicalprescriptionservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/api/patient/list")
    public ApiResponse<PatientListResponse> getPatientList(@RequestBody PatientListRequest request) {
        // 调用服务层获取患者列表
        PatientListResponse response = patientService.getPatientList(request);

        // 返回响应
        return ApiResponse.success(response);
    }
}
