package com.julien.medicalprescriptionservice.dto;

import lombok.Data;

@Data
public class PatientInfoResponse {

    private Integer patientId;    // 患者ID
    private String name;          // 姓名
    private String bedNumber;     // 床位号
    private String admissionNumber; // 入院编号
}