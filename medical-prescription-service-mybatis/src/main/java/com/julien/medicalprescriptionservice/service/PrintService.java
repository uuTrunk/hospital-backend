package com.julien.medicalprescriptionservice.service;

import org.springframework.stereotype.Service;

@Service
public class PrintService {

    // 模拟打印业务逻辑
    public String print(String recordId, String prescriptionId) {
        // 如果有病历 ID，优先打印病历
        if (recordId != null && !recordId.isEmpty()) {
            // 模拟打印病历
            System.out.println("正在打印病历: " + recordId);
        }
        // 如果有处方 ID，打印处方
        else if (prescriptionId != null && !prescriptionId.isEmpty()) {
            // 模拟打印处方
            System.out.println("正在打印处方: " + prescriptionId);
        } else {
            return "缺少打印信息，病历 ID 或处方 ID 必须提供";
        }

        // 返回打印任务已发起
        return "打印任务已发起";
    }
}
