package com.julien.medicalprescriptionservice.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.julien.medicalprescriptionservice.pojo.PatientInfo;
import com.julien.medicalprescriptionservice.dto.PatientQueryDTO;
import com.julien.medicalprescriptionservice.mapper.PatientInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientInfoService {

    @Autowired
    private PatientInfoMapper patientInfoMapper;

    public IPage<PatientInfo> getPatientList(PatientQueryDTO queryDTO) {
        // 创建分页对象
        Page<PatientInfo> page = new Page<>(queryDTO.getPage(), queryDTO.getPageSize());

        // 创建查询条件
        LambdaQueryWrapper<PatientInfo> queryWrapper = new LambdaQueryWrapper<>();
        if (queryDTO.getNameOrNumber() != null && !queryDTO.getNameOrNumber().isEmpty()) {
            queryWrapper.like(PatientInfo::getName, queryDTO.getNameOrNumber())
                    .or()
                    .like(PatientInfo::getAdmissionNumber, queryDTO.getNameOrNumber());
        }

        // 执行分页查询
        return patientInfoMapper.selectPage(page, queryWrapper);
    }
}
