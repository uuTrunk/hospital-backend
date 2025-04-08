package com.uutrunk.hospitalhealthdocument.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class DiagnosisPlan {
    @Id
    private Integer planId;
    
    private String recordId;
    
    private String treatmentPlan;
    
    private Integer recordDoctorId;
    
    private LocalDateTime recordTime;
}