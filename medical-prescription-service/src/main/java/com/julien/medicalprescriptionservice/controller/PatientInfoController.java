package com.julien.medicalprescriptionservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.julien.medicalprescriptionservice.pojo.PatientInfo;
import com.julien.medicalprescriptionservice.dto.PatientQueryDTO;
import com.julien.medicalprescriptionservice.service.PatientInfoService;
import com.julien.medicalprescriptionservice.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientInfoController {

    @Autowired
    private PatientInfoService patientInfoService;

    @GetMapping("/list")
    public ApiResponse getPatientList(PatientQueryDTO queryDTO) {
        // 调用 Service 层方法获取分页数据
        IPage<PatientInfo> page = patientInfoService.getPatientList(queryDTO);

        // 构建返回的响应数据
        return new ApiResponse(200, "成功", new ApiResponse.Data<>(page.getTotal(), page.getRecords()));
    }
}
