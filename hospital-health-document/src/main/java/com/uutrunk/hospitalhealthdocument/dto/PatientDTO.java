package com.uutrunk.hospitalhealthdocument.dto;

import com.uutrunk.hospitalhealthdocument.pojo.PatientInfo;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PatientDTO {
    private String name;
    private String bedNumber;
    private PatientInfo.CareGrade careGrade;

    public static PatientDTO fromEntity(PatientInfo entity) {
        return new PatientDTO()
            .setName(entity.getName())
            .setBedNumber(entity.getBedNumber())
            .setCareGrade(entity.getCareGrade());
    }
}