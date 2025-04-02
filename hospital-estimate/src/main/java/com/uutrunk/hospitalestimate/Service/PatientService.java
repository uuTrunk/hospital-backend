package com.uutrunk.hospitalestimate.Service;

import com.uutrunk.hospitalestimate.POJO.Patient;

public interface PatientService {
    /**
     * 根据患者ID获取患者信息
     *
     * @param patientId 患者ID
     * @return 患者对象，如果未找到则返回null
     */
    Patient getPatientById(int patientId);
}
