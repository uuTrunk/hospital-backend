package com.uutrunk.hospitalhealthdocument.dto;

import lombok.Data;

@Data
public class HealthRecordUpdateDTO {
    private String recordId;
    private HealthRecordDetailDTO healthRecordDetailDTO;
}
