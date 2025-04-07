package com.uutrunk.hospitalordermanagement.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MedicalOrderMain {
    @Id
    private String orderId;
    private Integer patientId;
    private Integer doctorId;
    private String orderType; // "临时"/"长期"
    private String content;
    private BigDecimal dosage;
    private Integer unitId;
    private Integer frequencyId;
    private LocalDateTime sendTime;
    private String status; // "待校对"/"已校对"/"已执行"/"已作废"
    private LocalDateTime startTime;
}