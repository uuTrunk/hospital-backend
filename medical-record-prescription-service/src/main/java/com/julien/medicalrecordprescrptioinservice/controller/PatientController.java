package com.julien.medicalrecordprescrptioinservice.controller;

import com.julien.medicalrecordprescrptioinservice.dto.PatientListItemDTO;
import com.julien.medicalrecordprescrptioinservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public Map<String, Object> getPatientList(
            @RequestParam(required = false) String name_or_number,
            @RequestParam int page,
            @RequestParam int pageSize
    ) {
        Page<PatientListItemDTO> resultPage = patientService.getPatients(name_or_number, page, pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "成功");

        Map<String, Object> data = new HashMap<>();
        data.put("total", resultPage.getTotalElements());
        data.put("list", resultPage.getContent());

        response.put("data", data);

        return response;
    }
}
