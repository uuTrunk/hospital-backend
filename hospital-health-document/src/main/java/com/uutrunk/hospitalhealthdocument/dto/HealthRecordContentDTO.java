package com.uutrunk.hospitalhealthdocument.dto;

import lombok.Data;

@Data
public class HealthRecordContentDTO {
    private AdmissionHistoryDTO admissionHistoryDTO;
    private DiagnosisPlanDTO diagnosisPlanDTO;
}
