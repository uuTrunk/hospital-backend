package com.julien.medicalrecordprescrptioinservice.repository;

import com.julien.medicalrecordprescrptioinservice.entity.PatientInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientInfoRepository extends JpaRepository<PatientInfo, Integer> {

    Page<PatientInfo> findByNameContainingIgnoreCaseOrAdmissionNumberContainingIgnoreCase(
            String name, String admissionNumber, Pageable pageable
    );
}
