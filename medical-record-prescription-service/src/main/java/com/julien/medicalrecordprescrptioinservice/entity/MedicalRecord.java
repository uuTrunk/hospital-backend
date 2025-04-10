package com.julien.medicalrecordprescrptioinservice.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_record")
@Data
public class MedicalRecord {

    @Id
    @Column(name = "record_id", length = 50)
    private String recordId; // 病历 ID（唯一标识）

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    private PatientInfo patientInfo; // 关联患者 ID

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private DoctorInfo doctorInfo; // 关联医生 ID

    @Column(name = "visit_date", nullable = false)
    private LocalDateTime visitDate; // 就诊日期

    @Column(name = "chief_complaint", columnDefinition = "TEXT")
    private String chiefComplaint; // 主诉

    @Column(name = "present_illness", columnDefinition = "TEXT")
    private String presentIllness; // 现病史

    @Column(name = "past_illness", columnDefinition = "TEXT")
    private String pastIllness; // 既往史

    @Column(name = "physical_exam", columnDefinition = "TEXT")
    private String physicalExam; // 体检情况

    @Column(name = "auxiliary_exam", columnDefinition = "TEXT")
    private String auxiliaryExam; // 辅助检查

    @Column(name = "treatment_opinion", columnDefinition = "TEXT")
    private String treatmentOpinion; // 处理意见

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public PatientInfo getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(PatientInfo patientInfo) {
        this.patientInfo = patientInfo;
    }

    public DoctorInfo getDoctorInfo() {
        return doctorInfo;
    }

    public void setDoctorInfo(DoctorInfo doctorInfo) {
        this.doctorInfo = doctorInfo;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public String getPresentIllness() {
        return presentIllness;
    }

    public void setPresentIllness(String presentIllness) {
        this.presentIllness = presentIllness;
    }

    public String getPastIllness() {
        return pastIllness;
    }

    public void setPastIllness(String pastIllness) {
        this.pastIllness = pastIllness;
    }

    public String getPhysicalExam() {
        return physicalExam;
    }

    public void setPhysicalExam(String physicalExam) {
        this.physicalExam = physicalExam;
    }

    public String getAuxiliaryExam() {
        return auxiliaryExam;
    }

    public void setAuxiliaryExam(String auxiliaryExam) {
        this.auxiliaryExam = auxiliaryExam;
    }

    public String getTreatmentOpinion() {
        return treatmentOpinion;
    }

    public void setTreatmentOpinion(String treatmentOpinion) {
        this.treatmentOpinion = treatmentOpinion;
    }


}
