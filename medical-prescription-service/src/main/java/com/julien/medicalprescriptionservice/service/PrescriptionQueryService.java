package com.julien.medicalprescriptionservice.service;

import com.julien.medicalprescriptionservice.dto.PrescriptionDetailResponse;
import com.julien.medicalprescriptionservice.mapper.PrescriptionDetailMapper;
import com.julien.medicalprescriptionservice.pojo.PrescriptionDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionQueryService {

    @Autowired
    private PrescriptionDetailMapper prescriptionDetailMapper; // MyBatis 映射器

    public PrescriptionDetailResponse getPrescriptionDetail(String prescriptionId) {
        // 获取处方详细信息
        List<PrescriptionDetail> details = prescriptionDetailMapper.selectByPrescriptionId(prescriptionId);

        // 将数据转换为响应对象
        PrescriptionDetailResponse response = new PrescriptionDetailResponse();
        response.setPatientName("李大爷"); // 示例患者姓名
        response.setDoctorName("王医生"); // 示例医生姓名
        response.setDiagnosis("高血压"); // 示例诊断
        response.setPrescriptionDate("2024-05-16 12:56:00"); // 示例处方日期
        response.setDetailList(details.stream().map(detail -> {
            PrescriptionDetailResponse.PrescriptionDetailItem item = new PrescriptionDetailResponse.PrescriptionDetailItem();
            item.setDrugName(detail.getDrugName());
            item.setSpecification(detail.getSpecification());
            item.setDosage(detail.getDosage());
            item.setUsage(detail.getUsage());
            return item;
        }).collect(Collectors.toList()));

        return response;
    }
}
