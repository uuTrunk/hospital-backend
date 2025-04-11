package com.julien.medicalrecordprescrptioinservice.controller;

import com.julien.medicalrecordprescrptioinservice.common.ResponseResult;
import com.julien.medicalrecordprescrptioinservice.dto.PrescriptionCreateRequestDTO;
import com.julien.medicalrecordprescrptioinservice.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/create")
    public ResponseResult<?> createPrescription(@RequestBody PrescriptionCreateRequestDTO request) {
        prescriptionService.createPrescription(request);
        return ResponseResult.success("处方创建成功");
    }
}
