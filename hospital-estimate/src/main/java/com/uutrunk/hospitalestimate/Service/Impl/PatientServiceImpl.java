package com.uutrunk.hospitalestimate.Service.Impl;

import com.uutrunk.hospitalestimate.POJO.Patient;
import com.uutrunk.hospitalestimate.Service.PatientService;
import com.uutrunk.hospitalestimate.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public Patient getPatientById(int patientId) {
        // 调用Mapper层方法获取患者信息
        return patientMapper.selectById(patientId);
    }
}
