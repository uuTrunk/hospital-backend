package com.uutrunk.hospitalordermanagement.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalOrderUpdateDTO {
    @TableId(value = "order_id", type = IdType.AUTO)
    @NotBlank(message = "医嘱ID不能为空")
    private String orderId;
    
    private String content;
    private String dosage;
    private String usage;
    private String frequency;
    private String doctorName;
}