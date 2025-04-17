package com.uutrunk.hospitalordermanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.uutrunk.hospitalordermanagement.enums.OrderType;
import com.uutrunk.hospitalordermanagement.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalOrderMain {
    @TableId(value = "order_id", type = IdType.AUTO)
    private String orderId;
    @TableField(value = "patient_id")
    private Integer patientId;
    @TableField(value = "doctor_id")
    private Integer doctorId;
    @TableField(value = "order_type")
    private OrderType orderType; // "临时"/"长期"
    private String content;
    private String dosage;
    @TableField(value = "medical_usage")
    private String medicalUsage;
    private String frequency;
    @TableField(value = "validity_period")
    private String validityPeriod; // 临时医嘱必填
    @TableField(value = "stopping_time")
    private LocalDateTime stoppingTime;
    @TableField(value = "order_status")
    private Status orderStatus; // "待校对"/"已校对"/"已执行"/"已作废"
    @TableField(value = "starting_time")
    private LocalDateTime startingTime;
}