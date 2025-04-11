package com.julien.medicalrecordprescrptioinservice.repository;

import com.julien.medicalrecordprescrptioinservice.entity.PrescriptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionDetailRepository extends JpaRepository<PrescriptionDetail, Integer> {
    List<PrescriptionDetail> findByPrescriptionMain_PrescriptionId(String prescriptionId);

}
