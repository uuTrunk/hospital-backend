package com.julien.medicalprescriptionservice.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 诊断字典实体类，对应数据库表 diagnosis_dict
 */
@Entity
@Table(name = "diagnosis_dict", uniqueConstraints = @UniqueConstraint(columnNames = "diagnosis_name"))
@Data
public class DiagnosisDict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_id")
    private Integer diagnosisId; // 诊断 ID（自增）

    @Column(name = "diagnosis_name", length = 100, nullable = false, unique = true)
    private String diagnosisName; // 诊断名称（如高血压、上呼吸道感染）
}
