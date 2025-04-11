package com.julien.medicalrecordprescrptioinservice.repository;

import com.julien.medicalrecordprescrptioinservice.entity.DoctorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorInfoRepository extends JpaRepository<DoctorInfo, Integer> {
}
