package com.julien.hospitaldischargeservice.controller;

import com.julien.hospitaldischargeservice.common.ApiResponse;
import com.julien.hospitaldischargeservice.common.PageResult;
import com.julien.hospitaldischargeservice.dto.DischargeDTO;
import com.julien.hospitaldischargeservice.service.DischargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/discharge")
public class DischargeController {

    @Autowired
    private DischargeService dischargeService;

    @GetMapping("/list")
    public PageResult<DischargeDTO> getDischargeList(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) String nameOrCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<DischargeDTO> dischargePage = dischargeService.getDischargeList(
                startDate, endDate, nameOrCode, PageRequest.of(page, size));
        return PageResult.from(dischargePage);
    }

    @GetMapping("/{id}")
    public ApiResponse<DischargeDTO> getDischargeById(@PathVariable Integer id) {
        DischargeDTO discharge = dischargeService.getDischargeById(id);
        return discharge != null ? ApiResponse.success(discharge) : ApiResponse.error("未找到离院记录");
    }

    @GetMapping("/patient/{patientId}")
    public ApiResponse<DischargeDTO> getDischargeByPatientId(@PathVariable Integer patientId) {
        DischargeDTO discharge = dischargeService.getDischargeByPatientId(patientId);
        return discharge != null ? ApiResponse.success(discharge) : ApiResponse.error("未找到该患者的离院记录");
    }
}
