package com.julien.medicalprescriptionservice.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class MedicalRecordService {

    // 模拟填写病历的业务逻辑
    public String createMedicalRecord(int patientId, int doctorId, String chiefComplaint,
                                      String presentIllness, String diagnosis, String treatmentOpinion) {
        // 这里可以执行数据库操作，保存病历记录到数据库中
        // 例如：保存病历到数据库，并返回病历 ID

        // 模拟生成病历 ID
        String recordId = "REC_" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return recordId;
    }
}
