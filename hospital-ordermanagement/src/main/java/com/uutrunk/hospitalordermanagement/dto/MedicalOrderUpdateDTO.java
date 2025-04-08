package com.uutrunk.hospitalordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalOrderUpdateDTO {
    @NotBlank(message = "医嘱ID不能为空")
    private String orderId;
    
    private String content;
    private String dosage;
    private String usage;
    private String frequency;
    private int doctorId;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
}