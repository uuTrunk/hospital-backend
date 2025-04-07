package com.julien.medicalprescriptionservice.controller;

import com.julien.medicalprescriptionservice.dto.PrescriptionCreateRequest;
import com.julien.medicalprescriptionservice.pojo.PrescriptionDetail;
import com.julien.medicalprescriptionservice.pojo.PrescriptionMain;
import com.julien.medicalprescriptionservice.service.PrescriptionService;
import com.julien.medicalprescriptionservice.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/api/prescription/create")
    public ApiResponse createPrescription(@RequestBody PrescriptionCreateRequest request) {
        // 创建处方主表对象
        PrescriptionMain prescriptionMain = new PrescriptionMain();
        prescriptionMain.setPatientId(request.getPatientId());
        prescriptionMain.setDoctorId(request.getDoctorId());
        prescriptionMain.setDiagnosis(request.getDiagnosis());

        // 将请求中的处方明细转换为 PrescriptionDetail 对象
        List<PrescriptionDetail> prescriptionDetails = request.getPrescriptionDetail().stream().map(detail -> {
            PrescriptionDetail prescriptionDetail = new PrescriptionDetail();
            prescriptionDetail.setDrugName(detail.getDrugName());
            prescriptionDetail.setSpecification(detail.getSpecification());
            prescriptionDetail.setDosage(detail.getDosage());
            prescriptionDetail.setUsage(detail.getUsage());
            prescriptionDetail.setFrequency(detail.getFrequency());
            prescriptionDetail.setDays(detail.getDays());
            prescriptionDetail.setTotalQuantity(detail.getTotalQuantity());
            return prescriptionDetail;
        }).toList();

        // 调用 Service 层创建处方
        String prescriptionId = prescriptionService.createPrescription(prescriptionMain, prescriptionDetails);

        // 返回响应
        return new ApiResponse(200, "处方开具成功", new ApiResponse.Data<>(1, List.of(new PrescriptionCreateResponse(prescriptionId))));
    }

    public static class PrescriptionCreateResponse {
        private String prescriptionId;

        public PrescriptionCreateResponse(String prescriptionId) {
            this.prescriptionId = prescriptionId;
        }

        // Getter and Setter
    }
}
