package com.julien.medicalrecordprescrptioinservice.service.impl;

import com.julien.medicalrecordprescrptioinservice.dto.PatientListItemResponse;
import com.julien.medicalrecordprescrptioinservice.entity.PatientInfo;
import com.julien.medicalrecordprescrptioinservice.repository.PatientInfoRepository;
import com.julien.medicalrecordprescrptioinservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientInfoRepository patientInfoRepository;

    @Override
    public Page<PatientListItemResponse> getPatientList(String nameOrNumber, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("patientId").descending());

        Page<PatientInfo> patientPage = patientInfoRepository
                .findByNameContainingIgnoreCaseOrAdmissionNumberContainingIgnoreCase(
                        nameOrNumber == null ? "" : nameOrNumber,
                        nameOrNumber == null ? "" : nameOrNumber,
                        pageable);

        return patientPage.map(patient -> {
            PatientListItemResponse dto = new PatientListItemResponse();
            dto.setPatientId(patient.getPatientId());
            dto.setName(patient.getName());
            dto.setBedNumber(patient.getBedNumber());
            dto.setAdmissionNumber(patient.getAdmissionNumber());
            return dto;
        });
    }
}
