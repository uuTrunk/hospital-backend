package com.julien.medicalrecordprescrptioinservice.controller;

import com.julien.medicalrecordprescrptioinservice.dto.PatientListItemResponse;
import com.julien.medicalrecordprescrptioinservice.service.PatientService;
import com.julien.medicalrecordprescrptioinservice.vo.ApiResponse;
import com.julien.medicalrecordprescrptioinservice.vo.ApiResponse.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public ApiResponse getPatientList(
            @RequestParam(required = false) String name_or_number,
            @RequestParam int page,
            @RequestParam int pageSize
    ) {
        Page<PatientListItemResponse> resultPage = patientService.getPatientList(name_or_number, page, pageSize);
        Data data = new Data(resultPage.getTotalElements(), resultPage.getContent());
        return ApiResponse.success(data);
    }
}
