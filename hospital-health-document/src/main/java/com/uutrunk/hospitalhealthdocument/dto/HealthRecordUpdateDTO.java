package com.uutrunk.hospitalhealthdocument.dto;

import lombok.Data;

@Data
public class HealthRecordUpdateDTO {
    private Integer recordId;
    private HealthRecordDTO healthRecordDTO;
    private HealthRecordContentDTO healthRecordContentDTO;
}
