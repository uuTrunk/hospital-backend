package com.julien.hospitaldischargeservice.service.impl;

import com.julien.hospitaldischargeservice.entity.PatientInfo;
import com.julien.hospitaldischargeservice.mapper.PatientInfoMapper;
import com.julien.hospitaldischargeservice.service.PatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientInfoServiceImpl implements PatientInfoService {

    @Autowired
    private PatientInfoMapper patientInfoMapper;

    @Override
    public PatientInfo getPatientInfoByDischargeId(Integer dischargeId) {
        return patientInfoMapper.getPatientInfoByDischargeId(dischargeId);
    }
} 