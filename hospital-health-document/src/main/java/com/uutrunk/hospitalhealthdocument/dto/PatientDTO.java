package com.uutrunk.hospitalhealthdocument.dto;

import com.uutrunk.hospitalhealthdocument.enums.CareGrade;
import com.uutrunk.hospitalhealthdocument.pojo.PatientInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class PatientDTO implements Serializable {
    private static final long serialVersionUID = 2L;
    private String name;
    private String bedNumber;
    private CareGrade careGrade;
    private Integer age;
    private String admissionNumber;
    private LocalDateTime admissionDate;
}