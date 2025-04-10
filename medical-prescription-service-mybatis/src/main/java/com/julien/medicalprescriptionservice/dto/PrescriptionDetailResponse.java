package com.julien.medicalprescriptionservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrescriptionDetailResponse {

    private String patientName; // 患者姓名
    private String doctorName; // 医生姓名
    private String diagnosis; // 诊断信息
    private String prescriptionDate; // 处方日期
    private List<PrescriptionDetailItem> detailList; // 处方明细列表

    @Data
    public static class PrescriptionDetailItem {
        private String drugName; // 药品名称
        private String specification; // 规格
        private String dosage; // 单次剂量
        private String usage; // 用法
    }
}
