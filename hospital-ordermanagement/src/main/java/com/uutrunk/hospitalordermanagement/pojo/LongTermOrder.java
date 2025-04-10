package com.uutrunk.hospitalordermanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class LongTermOrder {
    @TableId(value = "order_id", type = IdType.AUTO)
    private String orderId;
    private LocalDateTime stopTime; // 停止时间
}