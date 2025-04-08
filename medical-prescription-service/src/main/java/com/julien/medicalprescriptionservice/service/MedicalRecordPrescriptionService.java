package com.julien.medicalprescriptionservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.julien.medicalprescriptionservice.pojo.MedicalRecord;
import com.julien.medicalprescriptionservice.pojo.PrescriptionMain;
import com.julien.medicalprescriptionservice.mapper.MedicalRecordMapper;
import com.julien.medicalprescriptionservice.mapper.PrescriptionMainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordPrescriptionService {

    @Autowired
    private PrescriptionMainMapper prescriptionMainMapper;

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    public List<PrescriptionMain> getPrescriptionsByPatientId(Integer patientId) {
        return prescriptionMainMapper.selectList(new QueryWrapper<PrescriptionMain>().eq("patient_id", patientId));
    }

    public List<MedicalRecord> getMedicalRecordsByPatientId(Integer patientId) {
        return medicalRecordMapper.selectList(new QueryWrapper<MedicalRecord>().eq("patient_id", patientId));
    }
}
