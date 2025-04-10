package com.julien.medicalrecordprescrptioinservice.dto;

public class PatientListItemResponse {
    private Integer patientId; // 患者 ID
    private String name; // 姓名
    private String bedNumber; // 房间床位
    private String admissionNumber; // 入院编号

    // Getter for patientId
    public Integer getPatientId() {
        return patientId;
    }

    // Setter for patientId
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for bedNumber
    public String getBedNumber() {
        return bedNumber;
    }

    // Setter for bedNumber
    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    // Getter for admissionNumber
    public String getAdmissionNumber() {
        return admissionNumber;
    }

    // Setter for admissionNumber
    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }
}
