package com.julien.medicalprescriptionservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.julien.medicalprescriptionservice.dto.PatientListRequestDTO;
import com.julien.medicalprescriptionservice.dto.PatientListResponseDTO;
import com.julien.medicalprescriptionservice.pojo.PatientInfo;
import com.julien.medicalprescriptionservice.mapper.PatientInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientInfoMapper patientInfoMapper;

    public PatientListResponseDTO getPatientList(PatientListRequestDTO requestDTO) {
        // 创建分页对象
        Page<PatientInfo> page = new Page<>(requestDTO.getPage(), requestDTO.getPageSize());

        // 根据模糊查询姓名或入院编号进行查询
        Page<PatientInfo> patientPage = patientInfoMapper.selectPage(
                page,
                new QueryWrapper<PatientInfo>().like("name", requestDTO.getNameOrNumber())
                        .or().like("admission_number", requestDTO.getNameOrNumber())
        );

        // 转换为响应DTO
        PatientListResponseDTO responseDTO = new PatientListResponseDTO();
        responseDTO.setTotal((int) patientPage.getTotal());

        List<PatientListResponseDTO.PatientInfoDTO> patientInfoDTOList = patientPage.getRecords().stream()
                .map(patient -> {
                    PatientListResponseDTO.PatientInfoDTO dto = new PatientListResponseDTO.PatientInfoDTO();
                    dto.setPatientId(patient.getPatientId());
                    dto.setName(patient.getName());
                    dto.setBedNumber(patient.getBedNumber());
                    dto.setAdmissionNumber(patient.getAdmissionNumber());
                    return dto;
                })
                .collect(Collectors.toList());

        responseDTO.setList(patientInfoDTOList);

        return responseDTO;
    }
}
