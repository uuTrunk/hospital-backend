package com.julien.medicalrecordprescrptioinservice.dto;

public class MedicalRecordCreateDTO {
    private int patientId;
    private int doctorId;
    private String chiefComplaint;
    private String presentIllness;
    private String pastIllness;      // 新增字段
    private String physicalExam;     // 新增字段
    private String auxiliaryExam;
    private String treatmentOpinion;

    // Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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
