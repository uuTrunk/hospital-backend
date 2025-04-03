package com.uutrunk.hospitalordermanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 医嘱主表
 * @TableName medical_order_main
 */
@TableName(value ="medical_order_main")
@Data
public class MedicalOrderMain {
    /**
     * 医嘱 ID（唯一标识）
     */
    @TableId
    private String order_id;

    /**
     * 关联患者 ID
     */
    private Integer patient_id;

    /**
     * 关联医生 ID
     */
    private Integer doctor_id;

    /**
     * 医嘱类型
     */
    private Object order_type;

    /**
     * 医嘱内容
     */
    private String content;

    /**
     * 单次剂量
     */
    private String dosage;

    /**
     * 用法（如口服、静脉滴注）
     */
    private String usage;

    /**
     * 频次（如每天 3 次）
     */
    private String frequency;

    /**
     * 发送时间
     */
    private Date send_time;

    /**
     * 状态
     */
    private Object status;

    /**
     * 开始时间
     */
    private Date start_time;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MedicalOrderMain other = (MedicalOrderMain) that;
        return (this.getOrder_id() == null ? other.getOrder_id() == null : this.getOrder_id().equals(other.getOrder_id()))
            && (this.getPatient_id() == null ? other.getPatient_id() == null : this.getPatient_id().equals(other.getPatient_id()))
            && (this.getDoctor_id() == null ? other.getDoctor_id() == null : this.getDoctor_id().equals(other.getDoctor_id()))
            && (this.getOrder_type() == null ? other.getOrder_type() == null : this.getOrder_type().equals(other.getOrder_type()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getDosage() == null ? other.getDosage() == null : this.getDosage().equals(other.getDosage()))
            && (this.getUsage() == null ? other.getUsage() == null : this.getUsage().equals(other.getUsage()))
            && (this.getFrequency() == null ? other.getFrequency() == null : this.getFrequency().equals(other.getFrequency()))
            && (this.getSend_time() == null ? other.getSend_time() == null : this.getSend_time().equals(other.getSend_time()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getStart_time() == null ? other.getStart_time() == null : this.getStart_time().equals(other.getStart_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrder_id() == null) ? 0 : getOrder_id().hashCode());
        result = prime * result + ((getPatient_id() == null) ? 0 : getPatient_id().hashCode());
        result = prime * result + ((getDoctor_id() == null) ? 0 : getDoctor_id().hashCode());
        result = prime * result + ((getOrder_type() == null) ? 0 : getOrder_type().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getDosage() == null) ? 0 : getDosage().hashCode());
        result = prime * result + ((getUsage() == null) ? 0 : getUsage().hashCode());
        result = prime * result + ((getFrequency() == null) ? 0 : getFrequency().hashCode());
        result = prime * result + ((getSend_time() == null) ? 0 : getSend_time().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getStart_time() == null) ? 0 : getStart_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", order_id=").append(order_id);
        sb.append(", patient_id=").append(patient_id);
        sb.append(", doctor_id=").append(doctor_id);
        sb.append(", order_type=").append(order_type);
        sb.append(", content=").append(content);
        sb.append(", dosage=").append(dosage);
        sb.append(", usage=").append(usage);
        sb.append(", frequency=").append(frequency);
        sb.append(", send_time=").append(send_time);
        sb.append(", status=").append(status);
        sb.append(", start_time=").append(start_time);
        sb.append("]");
        return sb.toString();
    }
}