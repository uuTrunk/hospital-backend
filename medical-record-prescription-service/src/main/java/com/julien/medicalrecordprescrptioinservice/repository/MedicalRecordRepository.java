package com.julien.medicalrecordprescrptioinservice.repository;

import com.julien.medicalrecordprescrptioinservice.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, String> {

    // 通过患者 ID 获取病历记录
    List<MedicalRecord> findByPatientInfoPatientId(Integer patientId);
}
