package com.julien.medicalprescriptionservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 对应数据库表：prescription_main
 */
@Entity
@Table(name = "prescription_main")
@Data
public class PrescriptionMain {

    @Id
    @Column(name = "prescription_id", length = 50)
    private String prescriptionId; // 处方 ID（主键，唯一标识）

    /**
     * 关联到患者信息表（patient_info）
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientInfo patientInfo; // 关联患者 ID（外键）

    /**
     * 关联到医生信息表（doctor_info）
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorInfo doctorInfo; // 关联医生 ID（外键）

    @Column(name = "diagnosis", columnDefinition = "TEXT")
    private String diagnosis; // 临床诊断（多个诊断用分隔符区分）

    @Column(name = "prescription_date", nullable = false, updatable = false)
    private LocalDateTime prescriptionDate = LocalDateTime.now(); // 处方日期（默认当前时间）

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PrescriptionStatus status; // 发药状态（ENUM）

    /**
     * 发药状态枚举
     */
    public enum PrescriptionStatus {
        未发药, 已发药
    }
}
