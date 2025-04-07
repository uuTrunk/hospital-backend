package com.uutrunk.hospitalordermanagement.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PatientInfo {
    @Id
    private Integer patientId;
    private String name;
    private String idNumber;
    private String bedNumber;
}