package com.uutrunk.hospitalordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalOrderQueryDTO {
    private String orderType;
    private String status;
    private String patientName;
    
    @Min(1)
    private Integer page = 1;
    
    @Min(1)
    private Integer pageSize = 10;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTimeStart;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTimeEnd;
}