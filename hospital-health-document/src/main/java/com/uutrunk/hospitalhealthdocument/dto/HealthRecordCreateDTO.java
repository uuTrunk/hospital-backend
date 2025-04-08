// 修改参数校验注解
package com.uutrunk.hospitalhealthdocument.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class HealthRecordCreateDTO {
    @NotNull(message = "患者ID不能为空")
    private Integer patientId;

    @NotNull(message = "建档医生ID不能为空")
    private Integer createDoctorId;

    // 基础信息字段需要明确结构，建议拆分为具体字段
    // 此处暂时保留原字段但添加注解
    @NotNull(message = "基础信息不能为空")
    private String basicInfo;
}