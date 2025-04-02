package com.uutrunk.hospitalestimate.VO;

import lombok.Data;

import java.util.Date;

@Data
public class AdmissionAssessmentListRequest {
    private Date startDate;
    private Date endDate;
    private String nameOrCode;
    private int page;
    private int pageSize;

    // Getters and Setters
}
