package com.julien.medicalprescriptionservice.service;

import com.julien.medicalprescriptionservice.dto.CreatePrescriptionRequest;
import com.julien.medicalprescriptionservice.dto.CreatePrescriptionResponse;
import com.julien.medicalprescriptionservice.pojo.PrescriptionMain;
import com.julien.medicalprescriptionservice.pojo.PrescriptionDetail;
import com.julien.medicalprescriptionservice.mapper.PrescriptionMainMapper;
import com.julien.medicalprescriptionservice.mapper.PrescriptionDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrescriptionCreationService {

    @Autowired
    private PrescriptionMainMapper prescriptionMainMapper;

    @Autowired
    private PrescriptionDetailMapper prescriptionDetailMapper;

    @Transactional
    public CreatePrescriptionResponse createPrescription(CreatePrescriptionRequest request) {
        // 1. 创建处方主表记录
        PrescriptionMain prescriptionMain = new PrescriptionMain();
        prescriptionMain.setPrescriptionId(generatePrescriptionId()); // 自定义生成处方ID的方法
        prescriptionMain.setPatientId(request.getPatientId());
        prescriptionMain.setDoctorId(request.getDoctorId());
        prescriptionMain.setDiagnosis(request.getDiagnosis());
        prescriptionMain.setStatus(PrescriptionMain.PrescriptionStatus.未发药);

        // 保存处方主表记录
        prescriptionMainMapper.insert(prescriptionMain);

        // 2. 创建处方明细记录
        List<PrescriptionDetail> details = request.getPrescriptionDetail().stream().map(detailRequest -> {
            PrescriptionDetail detail = new PrescriptionDetail();
            detail.setPrescriptionId(prescriptionMain.getPrescriptionId());
            detail.setDrugName(detailRequest.getDrugName());
            detail.setSpecification(detailRequest.getSpecification());
            detail.setDosage(detailRequest.getDosage());
            detail.setUsage(detailRequest.getUsage());
            detail.setFrequency(detailRequest.getFrequency());
            detail.setDays(detailRequest.getDays());
            detail.setTotalQuantity(detailRequest.getTotalQuantity());
            return detail;
        }).toList();

        // 保存处方明细记录
        prescriptionDetailMapper.insertBatch(details);

        // 返回新增的处方ID
        CreatePrescriptionResponse response = new CreatePrescriptionResponse();
        response.setPrescriptionId(prescriptionMain.getPrescriptionId());

        return response;
    }

    // 假设自定义生成处方ID的方法
    private String generatePrescriptionId() {
        // 生成一个唯一的处方ID，例如通过时间戳或者UUID
        return "PRES_" + System.currentTimeMillis();
    }
}
