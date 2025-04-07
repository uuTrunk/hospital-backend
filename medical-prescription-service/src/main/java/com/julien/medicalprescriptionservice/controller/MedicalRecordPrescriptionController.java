package com.julien.medicalprescriptionservice.controller;

import com.julien.medicalprescriptionservice.pojo.MedicalRecord;
import com.julien.medicalprescriptionservice.pojo.PrescriptionMain;
import com.julien.medicalprescriptionservice.service.MedicalRecordPrescriptionService;
import com.julien.medicalprescriptionservice.vo.MedicalRecordPrescriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

@RestController
public class MedicalRecordPrescriptionController {

    @Autowired
    private MedicalRecordPrescriptionService medicalRecordPrescriptionService;

    @GetMapping("/api/medical-record-prescription/list")
    public MedicalRecordPrescriptionResponse getMedicalRecordPrescriptionList(@RequestParam("patientId") Integer patientId) {
        List<PrescriptionMain> prescriptions = medicalRecordPrescriptionService.getPrescriptionsByPatientId(patientId);
        List<MedicalRecord> medicalRecords = medicalRecordPrescriptionService.getMedicalRecordsByPatientId(patientId);

        // 合并处方和病历记录
        List<Object> result = new ArrayList<>();
        result.addAll(prescriptions);
        result.addAll(medicalRecords);

        // 返回符合格式的响应
        return new MedicalRecordPrescriptionResponse(200, "成功", result);
    }
}
