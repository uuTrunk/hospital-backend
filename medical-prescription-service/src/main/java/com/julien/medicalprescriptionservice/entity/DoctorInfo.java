package com.julien.medicalprescriptionservice.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 对应数据库表：doctor_info
 */
@Entity
@Table(name = "doctor_info")
@Data
public class DoctorInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctorId; // 医生 ID（主键，自增）

    @Column(name = "name", length = 50, nullable = false)
    private String name; // 姓名

    @Column(name = "department", length = 50, nullable = false)
    private String department; // 所属科室
}
