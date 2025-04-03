package com.julien.medicalprescriptionservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "patient_info")
@Data
public class PatientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId; // 主键，自增

    @Column(name = "name", length = 50, nullable = false)
    private String name; // 姓名

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false )
    private Gender gender; // 性别

    @Column(name = "age")
    private Integer age; // 年龄

    @Column(name = "bed_number", length = 20)
    private String bedNumber; // 房间床位

    @Column(name = "admission_number", length = 50, unique = true)
    private String admissionNumber; // 入院编号

    @Column(name = "insurance_type", length = 20)
    private String insuranceType; // 费别（如医保、自费）

    @Column(name = "insurance_id", length = 50)
    private String insuranceId; // 医保卡号

    /**
     * 性别 ENUM 类型
     */
    public enum Gender {
        男, 女
    }
}
