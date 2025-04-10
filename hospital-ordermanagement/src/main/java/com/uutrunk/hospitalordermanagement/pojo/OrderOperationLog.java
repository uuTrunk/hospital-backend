package com.uutrunk.hospitalordermanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class OrderOperationLog {
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;
    private String orderId;
    private String operationType; // 操作类型（如"作废"）
    private LocalDateTime operationTime;
    private Integer doctorId; // 操作人ID（医生/护士）
}