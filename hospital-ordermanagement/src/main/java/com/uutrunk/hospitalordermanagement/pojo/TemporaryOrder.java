package com.uutrunk.hospitalordermanagement.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TemporaryOrder {
    @Id
    private String orderId;
    private String validityPeriod; // 有效期描述
}