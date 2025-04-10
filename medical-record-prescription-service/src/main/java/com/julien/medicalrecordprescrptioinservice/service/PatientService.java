package com.julien.medicalrecordprescrptioinservice.service;

import com.julien.medicalrecordprescrptioinservice.dto.PatientListItemDTO;
import com.julien.medicalrecordprescrptioinservice.entity.PatientInfo;
import com.julien.medicalrecordprescrptioinservice.repository.PatientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientInfoRepository patientInfoRepository;

    public Page<PatientListItemDTO> getPatients(String keyword, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("patientId").descending());

        Page<PatientInfo> patientPage;
        if (keyword != null && !keyword.isBlank()) {
            patientPage = patientInfoRepository
                    .findByNameContainingIgnoreCaseOrAdmissionNumberContainingIgnoreCase(keyword, keyword, pageable);
        } else {
            patientPage = patientInfoRepository.findAll(pageable);
        }

        return patientPage.map(p -> new PatientListItemDTO(
                p.getPatientId(),
                p.getName(),
                p.getBedNumber(),
                p.getAdmissionNumber()
        ));
    }
}
