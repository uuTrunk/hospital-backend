package com.julien.medicalprescriptionservice.dto;

import lombok.Data;

@Data
public class MedicalRecordCreateRequest {

    private int patientId;        // 患者 ID
    private int doctorId;         // 医生 ID
    private String chiefComplaint; // 主诉
    private String presentIllness; // 现病史
    private String diagnosis;      // 临床诊断
    private String treatmentOpinion; // 处理意见
}
