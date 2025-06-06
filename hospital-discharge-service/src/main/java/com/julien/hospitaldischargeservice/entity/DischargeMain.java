package com.julien.hospitaldischargeservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

/**
 * 对应数据库表：discharge_main
 */
@Entity
@Table(name = "discharge_main")
@Data
public class DischargeMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discharge_id")
    private Integer dischargeId;

    /**
     * 外键关联到 patient_info 表的 patient_id
     * 你可以选择直接存 patientId，
     * 或者建立和 PatientInfo 实体的关联关系
     */

    // 建立多对一关系
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientInfo patientInfo;
    // 这里的 PatientInfo 为另一张表对应的实体类

    @Enumerated(EnumType.STRING)
    @Column(name = "discharge_reason", nullable = false)
    private DischargeReason dischargeReason;

    @Column(name = "discharge_date", nullable = false)
    private LocalDate dischargeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "summary_status", nullable = false)
    private SummaryStatus summaryStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "handover_status", nullable = false)
    private HandoverStatus handoverStatus;

    @OneToOne(mappedBy = "dischargeMain", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DischargeSummary dischargeSummary;

    @OneToMany(mappedBy = "dischargeMain", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DischargeHandover> dischargeHandovers;

    /**
     * 下面定义 ENUM 类型
     * 可以写成独立文件，也可以写在同一个文件中
     */
    public enum DischargeReason {
        自动出院,
        本院死亡,
        外院死亡,
        转院出院
    }

    public enum SummaryStatus {
        待录入,
        草稿,
        已提交
    }

    public enum HandoverStatus {
        待完成,
        已完成
    }
}
