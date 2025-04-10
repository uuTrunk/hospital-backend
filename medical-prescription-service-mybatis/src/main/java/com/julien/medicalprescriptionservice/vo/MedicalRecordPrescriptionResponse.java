package com.julien.medicalprescriptionservice.vo;

import java.util.List;

public class MedicalRecordPrescriptionResponse {

    private int code;
    private String message;
    private List<Object> data;

    public MedicalRecordPrescriptionResponse(int code, String message, List<Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters

}
