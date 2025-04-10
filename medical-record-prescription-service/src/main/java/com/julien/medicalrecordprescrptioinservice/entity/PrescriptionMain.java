package com.julien.medicalrecordprescrptioinservice.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prescription_main")
@Data
public class PrescriptionMain {

    @Id
    @Column(name = "prescription_id", length = 50)
    private String prescriptionId; // 处方 ID（唯一标识）

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
    private PatientInfo patientInfo; // 关联患者 ID

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id")
    private DoctorInfo doctorInfo; // 关联医生 ID

    @Column(name = "diagnosis", columnDefinition = "TEXT")
    private String diagnosis; // 临床诊断（多个诊断用分隔符区分）

    @Column(name = "prescription_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime prescriptionDate; // 处方日期

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private PrescriptionStatus status; // 发药状态

    public enum PrescriptionStatus {
        未发药, 已发药 // 发药状态
    }
}
