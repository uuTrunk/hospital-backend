package com.uutrunk.hospitalcommon.dto;

import com.uutrunk.hospitalcommon.enums.CareGrade;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@Accessors(chain = true)
public class PatientDTO {
    private String name;
    private String bedNumber;
    private CareGrade careGrade;
    private Integer age;
    private String admissionNumber;
    private LocalDateTime admissionDate;
}