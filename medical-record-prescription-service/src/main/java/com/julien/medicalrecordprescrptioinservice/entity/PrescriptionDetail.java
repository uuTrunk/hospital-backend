package com.julien.medicalrecordprescrptioinservice.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "prescription_detail")
@Data
public class PrescriptionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Integer detailId; // 处方明细 ID，自增

    @ManyToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "prescription_id")
    private PrescriptionMain prescriptionMain; // 关联处方主表 ID

    @Column(name = "drug_name", nullable = false, length = 100)
    private String drugName; // 药品名称

    @Column(name = "specification", length = 50)
    private String specification; // 规格（如`0.25g*20粒/盒`）

    @Column(name = "dosage", length = 50)
    private String dosage; // 单次剂量

    @Column(name = "`usage`", length = 50)
    private String usage; // 用量，如口服

    @Column(name = "frequency", length = 50)
    private String frequency; // 频次（如每天 3 次 (td)）

    @Column(name = "days")
    private Integer days; // 天数

    @Column(name = "total_quantity", length = 50)
    private String totalQuantity; // 总量

    public Integer getDetailId() {
        return detailId;
    }

    public PrescriptionMain getPrescriptionMain() {
        return prescriptionMain;
    }

    public String getSpecification() {
        return specification;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getUsage() {
        return usage;
    }

    public String getFrequency() {
        return frequency;
    }

    public Integer getDays() {
        return days;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public void setPrescriptionMain(PrescriptionMain prescriptionMain) {
        this.prescriptionMain = prescriptionMain;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
