package com.julien.hospitaldischargeservice.service;

import com.julien.hospitaldischargeservice.entity.PatientInfo;

public interface PatientInfoService {
    PatientInfo getPatientInfoByDischargeId(Integer dischargeId);
} 