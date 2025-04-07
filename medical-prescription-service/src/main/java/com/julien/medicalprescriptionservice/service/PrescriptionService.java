package com.julien.medicalprescriptionservice.service;

import com.julien.medicalprescriptionservice.pojo.PrescriptionDetail;
import com.julien.medicalprescriptionservice.pojo.PrescriptionMain;
import com.julien.medicalprescriptionservice.mapper.PrescriptionMainMapper;
import com.julien.medicalprescriptionservice.mapper.PrescriptionDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionMainMapper prescriptionMainMapper;

    @Autowired
    private PrescriptionDetailMapper prescriptionDetailMapper;

    @Transactional
    public String createPrescription(PrescriptionMain prescriptionMain, List<PrescriptionDetail> prescriptionDetails) {
        // 生成处方ID
        String prescriptionId = "PRES_" + System.currentTimeMillis();
        prescriptionMain.setPrescriptionId(prescriptionId);

        // 保存处方信息
        prescriptionMainMapper.insert(prescriptionMain);

        // 保存处方明细
        for (PrescriptionDetail detail : prescriptionDetails) {
            detail.setPrescriptionId(Integer.valueOf(prescriptionId));
            prescriptionDetailMapper.insert(detail);
        }

        return prescriptionId;
    }
}
