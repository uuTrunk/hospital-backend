package com.julien.medicalrecordprescrptioinservice.repository;

import com.julien.medicalrecordprescrptioinservice.entity.PrescriptionMain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionMainRepository extends JpaRepository<PrescriptionMain, String> {

    // 通过患者 ID 获取处方记录
    List<PrescriptionMain> findByPatientInfoPatientId(Integer patientId);

    Optional<PrescriptionMain> findByPrescriptionId(String prescriptionId);

}
