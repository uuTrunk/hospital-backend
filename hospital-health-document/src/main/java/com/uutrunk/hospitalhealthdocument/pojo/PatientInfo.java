package com.uutrunk.hospitalhealthdocument.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.uutrunk.hospitalhealthdocument.enums.CareGrade;
import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PatientInfo {
    @TableId("patient_id")
    private Integer patientId;
    
    private String name;
    
    private String gender;
    
    private Integer age;
    
    private String idCard;

    private Date birthDate;

    private LocalDateTime registrationDate;

    private String insuranceType;

    private String insuranceId;
    
    private String admissionNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime admissionDate;
    
    private String bedNumber;
    
    private String contactName;
    
    private String contactPhone;

    private CareGrade careGrade;

}