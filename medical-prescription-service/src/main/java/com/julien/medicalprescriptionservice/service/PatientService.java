package com.julien.medicalprescriptionservice.service;

import com.julien.medicalprescriptionservice.dto.PatientInfoResponse;
import com.julien.medicalprescriptionservice.pojo.PatientInfo;
import com.julien.medicalprescriptionservice.dto.PatientListRequest;
import com.julien.medicalprescriptionservice.dto.PatientListResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.julien.medicalprescriptionservice.mapper.PatientInfoMapper;

import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientInfoMapper patientInfoMapper;

    // 查询患者列表
    public PatientListResponse getPatientList(PatientListRequest request) {
        // 创建分页查询对象
        Page<PatientInfo> page = new Page<>(request.getPage(), request.getPageSize());

        // 创建查询条件
        QueryWrapper<PatientInfo> queryWrapper = new QueryWrapper<>();
        if (request.getNameOrNumber() != null && !request.getNameOrNumber().isEmpty()) {
            // 模糊查询患者姓名或入院编号
            queryWrapper.like("name", request.getNameOrNumber()).or().like("admission_number", request.getNameOrNumber());
        }

        // 执行查询
        Page<PatientInfo> patientPage = patientInfoMapper.selectPage(page, queryWrapper);

        // 封装响应
        PatientListResponse response = new PatientListResponse();
        response.setTotal(patientPage.getTotal());
        response.setList(patientPage.getRecords().stream()
                .map(patient -> {
                    PatientInfoResponse info = new PatientInfoResponse();
                    info.setPatientId(patient.getPatientId());
                    info.setName(patient.getName());
                    info.setBedNumber(patient.getBedNumber());
                    info.setAdmissionNumber(patient.getAdmissionNumber());
                    return info;
                })
                .collect(Collectors.toList()));

        return response;
    }
}
