package com.uutrunk.hospitalordermanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalOrderDTO {
    private String orderId;
    private String patientName;
    private String doctorName;
    private String orderType;
    private String content;
    private String dosage;
    private String usage;
    private String frequency;
    private String validityPeriod;
    private LocalDateTime stopTime;
    private String status;
    private LocalDateTime sendTime;
    private LocalDateTime startTime;
}