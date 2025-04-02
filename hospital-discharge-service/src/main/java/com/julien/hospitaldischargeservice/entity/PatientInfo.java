package com.julien.hospitaldischargeservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient_info")
@Data
public class PatientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "id_number", unique = true, length = 18)
    private String idNumber;

    @Column(name = "age")
    private Integer age;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "bed", nullable = false)
    private Integer bed;

    @Enumerated(EnumType.STRING)
    @Column(name = "care_grade", nullable = false)
    private CareGrade careGrade;

    // 枚举类
    public enum Gender {
        男, 女
    }

    public enum CareGrade {
        一级护理, 二级护理, 三级护理
    }
}
