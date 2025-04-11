package com.uutrunk.hospitalordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uutrunk.hospitalordermanagement.Enum.OrderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalOrderCreateDTO {
    @NotNull(message = "患者ID不能为空")
    private Integer patientId;
    
    @NotNull(message = "医生ID不能为空")
    private Integer doctorId;
    
    @NotBlank(message = "医嘱类型不能为空")
    private OrderType orderType;
    
    @NotBlank(message = "医嘱内容不能为空")
    private String content;
    
    private String dosage;
    private String usage;
    private String frequency;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @NotBlank(message = "临时医嘱必须填写有效期")
    @Pattern(regexp = ".*\\d+.*", message = "有效期需包含数字")
    private String validityPeriod; // 临时医嘱必填
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stopTime; // 长期医嘱必填


}