package com.julien.medicalrecordprescrptioinservice.service;

import com.julien.medicalrecordprescrptioinservice.dto.PatientListItemResponse;
import org.springframework.data.domain.Page;

public interface PatientService {
    Page<PatientListItemResponse> getPatientList(String nameOrNumber, int page, int pageSize);
}
