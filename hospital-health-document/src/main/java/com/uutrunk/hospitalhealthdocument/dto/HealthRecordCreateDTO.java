// 修改参数校验注解
package com.uutrunk.hospitalhealthdocument.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class HealthRecordCreateDTO {
    @NotNull(message = "患者ID不能为空")
    private Integer patientId;

    @NotNull(message = "建档医生ID不能为空")
    private String createDoctorName;

    @NotNull(message = "基础信息不能为空")
    private HealthRecordContentDTO basicInfo;
}