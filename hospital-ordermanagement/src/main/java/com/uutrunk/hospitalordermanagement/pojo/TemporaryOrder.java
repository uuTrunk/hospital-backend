package com.uutrunk.hospitalordermanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 临时医嘱表
 * @TableName temporary_order
 */
@TableName(value ="temporary_order")
@Data
public class TemporaryOrder {
    /**
     * 关联医嘱主表 ID
     */
    @TableId
    private String order_id;

    /**
     * 有效期（如 “24 小时内”）
     */
    private String validity_period;

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
        TemporaryOrder other = (TemporaryOrder) that;
        return (this.getOrder_id() == null ? other.getOrder_id() == null : this.getOrder_id().equals(other.getOrder_id()))
            && (this.getValidity_period() == null ? other.getValidity_period() == null : this.getValidity_period().equals(other.getValidity_period()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrder_id() == null) ? 0 : getOrder_id().hashCode());
        result = prime * result + ((getValidity_period() == null) ? 0 : getValidity_period().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", order_id=").append(order_id);
        sb.append(", validity_period=").append(validity_period);
        sb.append("]");
        return sb.toString();
    }
}