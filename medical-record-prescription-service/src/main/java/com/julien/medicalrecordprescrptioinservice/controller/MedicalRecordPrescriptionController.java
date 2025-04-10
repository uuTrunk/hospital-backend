package com.julien.medicalrecordprescrptioinservice.controller;

import com.julien.medicalrecordprescrptioinservice.dto.ApiResponse;
import com.julien.medicalrecordprescrptioinservice.dto.MedicalRecordPrescriptionDTO;
import com.julien.medicalrecordprescrptioinservice.service.MedicalRecordPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicalRecordPrescriptionController {

    @Autowired
    private MedicalRecordPrescriptionService service;

    @GetMapping("/api/medical-record-prescription/list")
    public ApiResponse<List<MedicalRecordPrescriptionDTO>> getMedicalRecordAndPrescriptionList(
            @RequestParam("patientId") Integer patientId) {

        List<MedicalRecordPrescriptionDTO> data = service.getMedicalRecordAndPrescriptionByPatientId(patientId);
        return new ApiResponse<>(200, "成功", data);
    }
}
