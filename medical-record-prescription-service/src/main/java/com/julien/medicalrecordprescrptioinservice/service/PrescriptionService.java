package com.julien.medicalrecordprescrptioinservice.service;

import com.julien.medicalrecordprescrptioinservice.dto.PrescriptionCreateRequestDTO;
import com.julien.medicalrecordprescrptioinservice.dto.PrescriptionDetailDTO;
import com.julien.medicalrecordprescrptioinservice.dto.PrescriptionDetailResponseDTO;
import com.julien.medicalrecordprescrptioinservice.entity.*;
import com.julien.medicalrecordprescrptioinservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionMainRepository prescriptionMainRepository;

    @Autowired
    private PrescriptionDetailRepository prescriptionDetailRepository;

    @Autowired
    private PatientInfoRepository patientInfoRepository;

    @Autowired
    private DoctorInfoRepository doctorInfoRepository;

    public void createPrescription(PrescriptionCreateRequestDTO request) {
        // 生成处方主表 ID
        String prescriptionId = "RX" + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();

        // 查询患者与医生
        PatientInfo patient = patientInfoRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("患者不存在"));
        DoctorInfo doctor = doctorInfoRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("医生不存在"));

        // 创建主表记录
        PrescriptionMain main = new PrescriptionMain();
        main.setPrescriptionId(prescriptionId);
        main.setPatientInfo(patient);
        main.setDoctorInfo(doctor);
        main.setDiagnosis(request.getDiagnosis());
        main.setPrescriptionDate(LocalDateTime.now());
        main.setStatus(PrescriptionMain.PrescriptionStatus.未发药);

        prescriptionMainRepository.save(main);

        // 插入每一个处方明细
        for (PrescriptionDetailDTO detailDTO : request.getPrescriptionDetail()) {
            PrescriptionDetail detail = new PrescriptionDetail();
            detail.setPrescriptionMain(main);
            detail.setDrugName(detailDTO.getDrugName());
            detail.setSpecification(detailDTO.getSpecification());
            detail.setDosage(detailDTO.getDosage());
            detail.setUsage(detailDTO.getUsage());
            detail.setFrequency(detailDTO.getFrequency());
            detail.setDays(detailDTO.getDays());
            detail.setTotalQuantity(detailDTO.getTotalQuantity());

            prescriptionDetailRepository.save(detail);
        }
    }

    public PrescriptionDetailResponseDTO getPrescriptionDetail(String prescriptionId) {
        PrescriptionMain main = prescriptionMainRepository.findByPrescriptionId(prescriptionId)
                .orElseThrow(() -> new RuntimeException("未找到处方"));

        List<PrescriptionDetail> detailList = prescriptionDetailRepository.findByPrescriptionMain_PrescriptionId(prescriptionId);

        PrescriptionDetailResponseDTO dto = new PrescriptionDetailResponseDTO();
        dto.setPatientName(main.getPatientInfo().getName());
        dto.setDoctorName(main.getDoctorInfo().getName());
        dto.setDiagnosis(main.getDiagnosis());
        dto.setPrescriptionDate(main.getPrescriptionDate());

        List<PrescriptionDetailResponseDTO.PrescriptionDetailItem> items = detailList.stream().map(detail -> {
            PrescriptionDetailResponseDTO.PrescriptionDetailItem item = new PrescriptionDetailResponseDTO.PrescriptionDetailItem();
            item.setDrugName(detail.getDrugName());
            item.setSpecification(detail.getSpecification());
            item.setDosage(detail.getDosage());
            item.setUsage(detail.getUsage());
            return item;
        }).toList();

        dto.setDetailList(items);

        return dto;
    }
}

