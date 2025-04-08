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
    private Integer summaryId; // 离院记录 ID（来自 `discharge_main` 表）

    /**
     * 外键关联到 discharge_main 表的 discharge_id
     * 这里使用 @ManyToOne 建立多对一关系
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discharge_id", nullable = false)
    private DischargeMain dischargeMain;

    @Column(name = "discharge_date", nullable = false)
    private LocalDate dischargeDate; // 离院日期或死亡日期

    @Column(name = "illness_name", length = 100, nullable = false)
    private String illnessName; // 疾病名称，死亡诊断或出院诊断

    @Enumerated(EnumType.STRING)
    @Column(name = "summary_type", nullable = false)
    private SummaryType summaryType; // 小结类型（'普通小结'或'死亡小结'）

    @Column(name = "admission_diagnosis", columnDefinition = "TEXT")
    private String admissionDiagnosis; // 入院诊断

    @Column(name = "in_hospital_condition", columnDefinition = "TEXT")
    private String inHospitalCondition; // 入院情况

    @Column(name = "treatment_process", columnDefinition = "TEXT")
    private String treatmentProcess; // 在院治疗过程

    @Column(name = "discharge_condition", columnDefinition = "TEXT")
    private String dischargeCondition; // 出院情况

    @Column(name = "discharge_advice", columnDefinition = "TEXT")
    private String dischargeAdvice; // 出院医嘱，出院小结填写

    @Column(name = "rescue_process", columnDefinition = "TEXT")
    private String rescueProcess; // 抢救过程，死亡小结填写

    @Column(name = "death_cause", columnDefinition = "TEXT")
    private String deathCause; // 死亡原因，死亡小结填写

    // 枚举：普通小结 or 死亡小结
    public enum SummaryType {
        普通小结,
        死亡小结
    }
}
