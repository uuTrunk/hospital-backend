package com.uutrunk.hospitalordermanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TemporaryOrder {
    @TableId(value = "order_id", type = IdType.AUTO)
    private String orderId;
    private String validityPeriod; // 有效期描述
}