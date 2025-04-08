package com.julien.hospitaldischargeservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DischargeListItemDTO {
    private Integer dischargeId;
    private String patientName;
    private String idNumber;
    private String gender;
    private Integer age;
    private LocalDate admissionDate;
    private String dischargeReason;
    private LocalDate dischargeDate;
    private String summaryStatus;
    private String handoverStatus;
}

