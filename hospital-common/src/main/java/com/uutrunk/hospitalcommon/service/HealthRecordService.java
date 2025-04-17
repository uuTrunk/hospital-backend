package com.uutrunk.hospitalcommon.service;

import com.uutrunk.hospitalcommon.dto.HealthRecordDetailDTO;
import org.springframework.stereotype.Repository;

// 新增文件：健康档案业务接口
@Repository
public interface HealthRecordService {
    HealthRecordDetailDTO getDetailByPatientId(Integer patientId);
}