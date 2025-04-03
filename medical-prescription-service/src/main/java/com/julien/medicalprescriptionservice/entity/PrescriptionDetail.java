package com.julien.medicalprescriptionservice.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 处方明细实体类，对应数据库表 prescription_detail
 */
@Entity
@Table(name = "prescription_detail")
@Data
public class PrescriptionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Integer detailId; // 处方明细 ID（主键，自增）

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private PrescriptionMain prescription; // 关联处方主表 ID（外键）

    @Column(name = "drug_name", length = 100, nullable = false)
    private String drugName; // 药品名称

    @Column(name = "specification", length = 50)
    private String specification; // 规格（如 0.25g*20 粒/盒）

    @Column(name = "dosage", length = 50)
    private String dosage; // 单次剂量

    @Column(name = "usage", length = 50)
    private String usage; // 用法（如口服 (po)）

    @Column(name = "frequency", length = 50)
    private String frequency; // 频次（如每天 3 次 (td)）

    @Column(name = "days")
    private Integer days; // 天数

    @Column(name = "total_quantity", length = 50)
    private String totalQuantity;
}
