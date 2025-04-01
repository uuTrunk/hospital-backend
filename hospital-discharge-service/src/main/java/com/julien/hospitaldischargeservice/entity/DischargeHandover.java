package com.julien.hospitaldischargeservice.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 对应数据库表：discharge_handover
 */
@Entity
@Table(name = "discharge_handover")
@Data
public class DischargeHandover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "handover_id")
    private Integer handoverId; // 交接ID，自增

    /**
     * 外键关联到 discharge_main 表的 discharge_id
     * 使用 @ManyToOne 建立多对一关系
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discharge_id", nullable = false)
    private DischargeMain dischargeMain;

    @Column(name = "handover_item", length = 50, nullable = false)
    private String handoverItem; // 交接项目

    @Enumerated(EnumType.STRING)
    @Column(name = "item_status", nullable = false)
    private ItemStatus itemStatus; // 项目状态（未完成、已完成）

    // 枚举定义
    public enum ItemStatus {
        未完成,
        已完成
    }
}
