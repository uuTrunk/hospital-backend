package com.julien.medicalprescriptionservice.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 药品信息实体类，对应数据库表 drug_info
 */
@Entity
@Table(name = "drug_info", uniqueConstraints = @UniqueConstraint(columnNames = "drug_name"))
@Data
public class DrugInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_id")
    private Integer drugId; // 药品 ID（自增）

    @Column(name = "drug_name", length = 100, nullable = false, unique = true)
    private String drugName; // 药品名称

    @Column(name = "specification", length = 50)
    private String specification; // 规格

    @Column(name = "contraindications", columnDefinition = "TEXT")
    private String contraindications; // 禁忌
}
