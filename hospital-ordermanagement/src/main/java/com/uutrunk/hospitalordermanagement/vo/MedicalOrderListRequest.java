package com.uutrunk.hospitalordermanagement.vo;

import lombok.Data;

@Data
public class MedicalOrderListRequest {
    private String orderType;
    private String status;
    private String patientName;
    private int page;
    private int pageSize;
}
