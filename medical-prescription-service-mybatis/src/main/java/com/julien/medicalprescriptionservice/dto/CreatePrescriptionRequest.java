package com.julien.medicalprescriptionservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreatePrescriptionRequest {

    private Integer patientId; // 患者 ID
    private Integer doctorId;  // 医生 ID
    private String diagnosis;  // 临床诊断
    private List<PrescriptionDetailRequest> prescriptionDetail; // 处方明细

    @Data
    public static class PrescriptionDetailRequest {

        private String drugName;     // 药品名称
        private String specification; // 规格
        private String dosage;        // 单次剂量
        private String usage;         // 用法
        private String frequency;     // 频次
        private Integer days;         // 天数
        private String totalQuantity; // 总量
    }
}
