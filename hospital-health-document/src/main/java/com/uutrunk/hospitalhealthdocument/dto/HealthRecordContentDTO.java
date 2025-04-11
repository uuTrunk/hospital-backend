package com.uutrunk.hospitalhealthdocument.dto;

import lombok.Data;

@Data
public class HealthRecordContentDTO {
    private String recordId;
    private AdmissionHistoryDTO admissionHistoryDTO;
    private DiagnosisPlanDTO diagnosisPlanDTO;
}
