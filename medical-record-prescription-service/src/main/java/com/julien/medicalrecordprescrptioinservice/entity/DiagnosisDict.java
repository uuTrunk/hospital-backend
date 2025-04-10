package com.julien.medicalrecordprescrptioinservice.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "diagnosis_dict")
@Data
public class DiagnosisDict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diagnosis_id")
    private Integer diagnosisId; // 诊断 ID，自增

    @Column(name = "diagnosis_name", nullable = false, length = 100, unique = true)
    private String diagnosisName; // 诊断名称（如高血压、上呼吸道感染）

    public Integer getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Integer diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }
}
