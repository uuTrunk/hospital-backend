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

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }
}
