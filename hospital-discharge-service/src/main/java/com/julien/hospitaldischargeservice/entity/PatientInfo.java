package com.julien.hospitaldischargeservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.julien.hospitaldischargeservice.entity.enums.Gender;
import com.julien.hospitaldischargeservice.entity.enums.CareGrade;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "patient_info")
@TableName("patient_info")
public class PatientInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(type = IdType.AUTO)
    private Integer patientId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String idNumber;

    private LocalDateTime registrationDate;

    private String bedNumber;

    @Enumerated(EnumType.STRING)
    private CareGrade careGrade;
}