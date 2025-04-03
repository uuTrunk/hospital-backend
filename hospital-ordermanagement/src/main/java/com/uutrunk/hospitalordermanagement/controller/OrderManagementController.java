package com.uutrunk.hospitalordermanagement.controller;

import com.uutrunk.hospitalordermanagement.service.MedicalOrderMainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderManagementController {
    private final MedicalOrderMainService medicalOrderMainService;

    public OrderManagementController(MedicalOrderMainService medicalOrderMainService) {
        this.medicalOrderMainService = medicalOrderMainService;
    }

    @GetMapping("/medicalOrderList")

}
