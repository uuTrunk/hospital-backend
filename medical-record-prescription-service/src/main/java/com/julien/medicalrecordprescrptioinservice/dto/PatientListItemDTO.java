package com.julien.medicalrecordprescrptioinservice.dto;

public class PatientListItemDTO {
    private Integer patientId;
    private String name;
    private String bedNumber;
    private String admissionNumber;

    public PatientListItemDTO() {
        // 必须的无参构造函数
    }

    public PatientListItemDTO(Integer patientId, String name, String bedNumber, String admissionNumber) {
        this.patientId = patientId;
        this.name = name;
        this.bedNumber = bedNumber;
        this.admissionNumber = admissionNumber;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }
}
