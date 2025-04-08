package com.julien.hospitaldischargeservice.dto;

import lombok.Data;

@Data
public class DischargeSummaryDTO {
    private String summaryType;
    private String admissionDiagnosis;
    private String inHospitalCondition;
    private String treatmentProcess;
    private String dischargeCondition;

    // 死亡小结的额外字段
    private String rescueProcess;
    private String deathCause;

    // 构造函数、Getters 和 Setters 由 Lombok 提供
}
