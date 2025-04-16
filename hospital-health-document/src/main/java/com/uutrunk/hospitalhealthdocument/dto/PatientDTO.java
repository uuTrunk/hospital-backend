package com.uutrunk.hospitalhealthdocument.dto;

import com.uutrunk.hospitalhealthdocument.pojo.PatientInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class PatientDTO {
    private String name;
    private String bedNumber;
    private PatientInfo.CareGrade careGrade;
    private Integer age;
    private String admissionNumber;
    private LocalDateTime admissionDate;
}