package com.julien.medicalprescriptionservice.controller;

import com.julien.medicalprescriptionservice.dto.PatientListRequestDTO;
import com.julien.medicalprescriptionservice.dto.PatientListResponseDTO;
import com.julien.medicalprescriptionservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/api/patient/list")
    public PatientListResponseDTO getPatientList(PatientListRequestDTO requestDTO) {
        return patientService.getPatientList(requestDTO);
    }
}
