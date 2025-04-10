package com.julien.medicalprescriptionservice.service;

import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

    // 发送处方至药房（模拟状态更新）
    public String sendPrescription(String prescriptionId) {
        // 模拟发送处方的业务逻辑
        // 这里可以进行数据库更新或调用外部系统接口
        // 例如：更新处方状态为“已发送”

        // 假设成功
        return "处方发送成功，药房已接收";
    }
}
