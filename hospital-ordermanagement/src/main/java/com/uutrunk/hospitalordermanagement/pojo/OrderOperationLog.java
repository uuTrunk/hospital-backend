package com.uutrunk.hospitalordermanagement.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class OrderOperationLog {
    @Id
    private Integer logId;
    private String orderId;
    private String operationType; // 操作类型（如"作废"）
    private LocalDateTime operationTime;
    private Integer doctorId; // 操作人ID（医生/护士）
}