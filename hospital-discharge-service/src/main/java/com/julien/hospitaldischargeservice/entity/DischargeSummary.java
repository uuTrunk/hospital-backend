package com.julien.hospitaldischargeservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

/**
 * 对应数据库表：discharge_summary
 */
@Entity
@Table(name = "discharge_summary")
@Data
public class DischargeSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "summary_id")
    private Integer summaryId; // 小结ID，自增

    /**
     * 外键关联到 discharge_main 表的 discharge_id
     * 这里使用 @ManyToOne 建立多对一关系
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discharge_id", nullable = false)
    private DischargeMain dischargeMain;

    @Column(name = "discharge_date", nullable = false)
    private LocalDate dischargeDate; // 离院日期

    @Column(name = "illness_name", length = 100, nullable = false)
    private String illnessName; // 疾病名称

    @Enumerated(EnumType.STRING)
    @Column(name = "summary_type")
    private SummaryType summaryType; // 小结类型

    @Column(name = "admission_diagnosis", columnDefinition = "TEXT")
    private String admissionDiagnosis; // 入院诊断

    @Column(name = "in_hospital_condition", columnDefinition = "TEXT")
    private String inHospitalCondition; // 住院情况

    @Column(name = "treatment_process", columnDefinition = "TEXT")
    private String treatmentProcess; // 治疗过程

    @Column(name = "discharge_condition", columnDefinition = "TEXT")
    private String dischargeCondition; // 出院情况

    @Column(name = "discharge_advice", columnDefinition = "TEXT")
    private String dischargeAdvice; // 出院医嘱

    @Column(name = "rescue_process", columnDefinition = "TEXT")
    private String rescueProcess; // 抢救过程

    @Column(name = "death_cause", columnDefinition = "TEXT")
    private String deathCause; // 死亡原因

    // 枚举：普通小结 or 死亡小结
    public enum SummaryType {
        普通小结,
        死亡小结
    }
}
