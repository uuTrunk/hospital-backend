package com.julien.medicalrecordprescrptioinservice.service;

import com.julien.medicalrecordprescrptioinservice.dto.MedicalRecordPrescriptionDTO;
import com.julien.medicalrecordprescrptioinservice.entity.MedicalRecord;
import com.julien.medicalrecordprescrptioinservice.entity.PrescriptionMain;
import com.julien.medicalrecordprescrptioinservice.repository.MedicalRecordRepository;
import com.julien.medicalrecordprescrptioinservice.repository.PrescriptionMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordPrescriptionService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PrescriptionMainRepository prescriptionMainRepository;

    /**
     * 获取患者的病历和处方记录
     *
     * @param patientId 患者 ID
     * @return 病历和处方列表
     */
    public List<MedicalRecordPrescriptionDTO> getMedicalRecordAndPrescriptionByPatientId(Integer patientId) {
        List<MedicalRecordPrescriptionDTO> result = new ArrayList<>();

        // 获取病历记录
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByPatientInfoPatientId(patientId);
        for (MedicalRecord record : medicalRecords) {
            MedicalRecordPrescriptionDTO dto = new MedicalRecordPrescriptionDTO();
            dto.setRecordId(record.getRecordId());
            dto.setVisitDate(record.getVisitDate());

            // 获取与病历相关的处方记录
            List<PrescriptionMain> prescriptions = prescriptionMainRepository.findByPatientInfoPatientId(patientId);
            for (PrescriptionMain prescription : prescriptions) {
                if (prescription.getPatientInfo().getPatientId().equals(patientId)) {
                    // 将诊断信息从处方记录添加到 DTO 中
                    dto.setDiagnosis(prescription.getDiagnosis());
                    dto.setPrescriptionId(prescription.getPrescriptionId());
                    dto.setPrescriptionDate(prescription.getPrescriptionDate());
                    dto.setDoctorName(prescription.getDoctorInfo().getName());
                }
            }

            result.add(dto);
        }

        return result;
    }
}
