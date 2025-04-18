package com.uutrunk.hospitalordermanagement.dto;

import com.uutrunk.hospitalordermanagement.enums.OrderType;
import com.uutrunk.hospitalordermanagement.enums.Status;
import com.uutrunk.hospitalordermanagement.pojo.MedicalOrderMain;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalOrderDTO {
    private String orderId;
    private String patientName;
    private String doctorName;
    private OrderType orderType;
    private String content;
    private String dosage;
    private String medicalUsage;
    private String frequency;
    private String validityPeriod;
    private LocalDateTime stopTime;
    private Status orderStatus;
    private LocalDateTime stoppingTime;
    private LocalDateTime startingTime;

    public MedicalOrderDTO fromMainEntity(MedicalOrderMain entity) {
        this.orderId = entity.getOrderId();
        this.orderType = entity.getOrderType();
        this.content = entity.getContent();
        this.dosage = entity.getDosage();
        this.medicalUsage = entity.getMedicalUsage();
        this.frequency = entity.getFrequency();
        this.orderStatus = entity.getOrderStatus();
        this.startingTime = entity.getStartingTime();
        return this;

    }
}