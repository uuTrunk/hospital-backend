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

    private Integer page = 1;

    private Integer pageSize = 10;
}