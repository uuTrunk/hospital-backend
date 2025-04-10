package com.julien.medicalprescriptionservice.controller;

import com.julien.medicalprescriptionservice.dto.MedicalRecordCreateRequest;
import com.julien.medicalprescriptionservice.dto.MedicalRecordCreateResponse;
import com.julien.medicalprescriptionservice.service.MedicalRecordService;
import com.julien.medicalprescriptionservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping("/api/medical-record/create")
    public ApiResponse<MedicalRecordCreateResponse> createMedicalRecord(@RequestBody MedicalRecordCreateRequest request) {
        // 调用服务层的业务逻辑创建病历
        String recordId = medicalRecordService.createMedicalRecord(
                request.getPatientId(),
                request.getDoctorId(),
                request.getChiefComplaint(),
                request.getPresentIllness(),
                request.getDiagnosis(),
                request.getTreatmentOpinion()
        );

        // 封装返回结果
        MedicalRecordCreateResponse response = new MedicalRecordCreateResponse();
        response.setRecordId(recordId);

        return ApiResponse.success(response);
    }
}
