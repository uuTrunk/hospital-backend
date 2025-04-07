package com.uutrunk.hospitalordermanagement.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class LongTermOrder {
    @Id
    private String orderId;
    private LocalDateTime stopTime; // 停止时间
}