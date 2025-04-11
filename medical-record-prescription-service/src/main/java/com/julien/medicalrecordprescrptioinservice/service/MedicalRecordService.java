package com.julien.medicalrecordprescrptioinservice.service;

import com.julien.medicalrecordprescrptioinservice.dto.MedicalRecordCreateDTO;
import com.julien.medicalrecordprescrptioinservice.entity.MedicalRecord;
import com.julien.medicalrecordprescrptioinservice.entity.DoctorInfo;
import com.julien.medicalrecordprescrptioinservice.entity.PatientInfo;
import com.julien.medicalrecordprescrptioinservice.repository.MedicalRecordRepository;
import com.julien.medicalrecordprescrptioinservice.repository.DoctorInfoRepository;
import com.julien.medicalrecordprescrptioinservice.repository.PatientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PatientInfoRepository patientInfoRepository;

    @Autowired
    private DoctorInfoRepository doctorInfoRepository;

    public String createMedicalRecord(MedicalRecordCreateDTO dto) {
        PatientInfo patient = patientInfoRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("患者未找到"));
        DoctorInfo doctor = doctorInfoRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("医生未找到"));


        MedicalRecord medicalRecord = new MedicalRecord();
        String generatedRecordId = "REC_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        medicalRecord.setRecordId(generatedRecordId);
        medicalRecord.setPatientInfo(patient);
        medicalRecord.setDoctorInfo(doctor);
        medicalRecord.setChiefComplaint(dto.getChiefComplaint());
        medicalRecord.setPresentIllness(dto.getPresentIllness());
        medicalRecord.setPastIllness(dto.getPastIllness());
        medicalRecord.setPhysicalExam(dto.getPhysicalExam());
        medicalRecord.setAuxiliaryExam(dto.getAuxiliaryExam());
        medicalRecord.setTreatmentOpinion(dto.getTreatmentOpinion());
        medicalRecord.setVisitDate(LocalDateTime.now());

        medicalRecordRepository.save(medicalRecord);
        return medicalRecord.getRecordId();
    }
}
