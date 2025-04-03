package com.uutrunk.hospitalordermanagement.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MedicalOrderListResponse {
    private String orderId;
    private String patientName;
    private String orderType;
    private String content;
    private String status;
    private Date sendTime;
}
