package com.julien.medicalrecordprescrptioinservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PrescriptionDetailResponseDTO {
    private String patientName;
    private String doctorName;
    private String diagnosis;
    private LocalDateTime prescriptionDate;
    private List<PrescriptionDetailItem> detailList;

    @Data
    public static class PrescriptionDetailItem {
        private String drugName;
        private String specification;
        private String dosage;
        private String usage;

        public String getDrugName() {
            return drugName;
        }

        public void setDrugName(String drugName) {
            this.drugName = drugName;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getDosage() {
            return dosage;
        }

        public void setDosage(String dosage) {
            this.dosage = dosage;
        }

        public String getUsage() {
            return usage;
        }

        public void setUsage(String usage) {
            this.usage = usage;
        }
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public LocalDateTime getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDateTime prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public List<PrescriptionDetailItem> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<PrescriptionDetailItem> detailList) {
        this.detailList = detailList;
    }
}
