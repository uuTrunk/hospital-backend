package com.uutrunk.hospitalhealthdocument.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class HealthRecordMain {
    @Id
    private String recordId;
    
    private Integer patientId;
    
    private Integer createdDoctorId;
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
    
    private String status;
}