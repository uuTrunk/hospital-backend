package com.julien.medicalprescriptionservice.controller;

import com.julien.medicalprescriptionservice.dto.CreatePrescriptionRequest;
import com.julien.medicalprescriptionservice.dto.CreatePrescriptionResponse;
import com.julien.medicalprescriptionservice.service.PrescriptionCreationService;
import com.julien.medicalprescriptionservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrescriptionCreationController {

    @Autowired
    private PrescriptionCreationService prescriptionCreationService;

    @PostMapping("/api/prescription/create")
    public ApiResponse<CreatePrescriptionResponse> createPrescription(
            @RequestBody CreatePrescriptionRequest request) {
        // 调用服务层创建处方
        CreatePrescriptionResponse response = prescriptionCreationService.createPrescription(request);

        // 返回成功响应
        return ApiResponse.success(response);
    }
}
