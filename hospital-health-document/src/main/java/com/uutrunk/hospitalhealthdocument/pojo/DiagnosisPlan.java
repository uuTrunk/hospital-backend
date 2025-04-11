package com.uutrunk.hospitalhealthdocument.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class DiagnosisPlan {
    @TableId(value = "plan_id", type = IdType.AUTO)
    private Integer planId;
    @TableField("record_id")
    private String recordId;
    @TableField("diagnosis")
    private String diagnosis;
    @TableField("treatment_plan")
    private String treatmentPlan;
    @TableField("record_doctor_name")
    private Integer recordDoctorName;
}