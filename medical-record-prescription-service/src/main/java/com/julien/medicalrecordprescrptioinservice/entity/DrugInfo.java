package com.julien.medicalrecordprescrptioinservice.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "drug_info")
@Data
public class DrugInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_id")
    private Integer drugId; // 药品 ID，自增

    @Column(name = "drug_name", nullable = false, length = 100, unique = true)
    private String drugName; // 药品名称

    @Column(name = "specification", length = 50)
    private String specification; // 规格

    @Column(name = "contraindications", columnDefinition = "TEXT")
    private String contraindications; // 禁忌
}
