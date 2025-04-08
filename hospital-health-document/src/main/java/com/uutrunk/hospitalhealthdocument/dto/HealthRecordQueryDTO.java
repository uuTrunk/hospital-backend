// 添加分页参数校验
package com.uutrunk.hospitalhealthdocument.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HealthRecordQueryDTO {
    private String patientName;
    private String recordStatus;

    @Min(1) @NotNull
    private Integer page;

    @Min(1) @Max(100)
    private Integer pageSize;
}