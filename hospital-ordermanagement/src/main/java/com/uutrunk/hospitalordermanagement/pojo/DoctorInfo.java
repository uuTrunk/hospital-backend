package com.uutrunk.hospitalordermanagement.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DoctorInfo {
    @Id
    private Integer doctorId;
    private String name;
    private String department;
}