package com.julien.medicalprescriptionservice.controller;

import com.julien.medicalprescriptionservice.dto.PrescriptionDetailRequest;
import com.julien.medicalprescriptionservice.dto.PrescriptionDetailResponse;
import com.julien.medicalprescriptionservice.service.PrescriptionQueryService;
import com.julien.medicalprescriptionservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrescriptionController {

    @Autowired
    private PrescriptionQueryService prescriptionQueryService;

    @GetMapping("/api/prescription/detail")
    public ApiResponse<PrescriptionDetailResponse> getPrescriptionDetail(@RequestBody PrescriptionDetailRequest request) {
        PrescriptionDetailResponse response = prescriptionQueryService.getPrescriptionDetail(request.getPrescriptionId());
        return ApiResponse.success(response);
    }
}
