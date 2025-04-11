package com.julien.medicalrecordprescrptioinservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class PrescriptionCreateRequestDTO {
    private Integer patientId;
    private Integer doctorId;
    private String diagnosis;
    private List<PrescriptionDetailDTO> prescriptionDetail;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<PrescriptionDetailDTO> getPrescriptionDetail() {
        return prescriptionDetail;
    }

    public void setPrescriptionDetail(List<PrescriptionDetailDTO> prescriptionDetail) {
        this.prescriptionDetail = prescriptionDetail;
    }
}
