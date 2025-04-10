package com.julien.medicalrecordprescrptioinservice.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "doctor_info")
@Data
public class DoctorInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctorId; // 医生 ID，自增

    @Column(name = "name", nullable = false, length = 50)
    private String name; // 姓名

    @Column(name = "department", nullable = false, length = 50)
    private String department; // 所属科室
}
