package com.uutrunk.hospitalordermanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 长期医嘱表
 * @TableName long_term_order
 */
@TableName(value ="long_term_order")
@Data
public class LongTermOrder {
    /**
     * 关联医嘱主表 ID
     */
    @TableId
    private String order_id;

    /**
     * 停止时间
     */
    private Date stop_time;

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
        LongTermOrder other = (LongTermOrder) that;
        return (this.getOrder_id() == null ? other.getOrder_id() == null : this.getOrder_id().equals(other.getOrder_id()))
            && (this.getStop_time() == null ? other.getStop_time() == null : this.getStop_time().equals(other.getStop_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrder_id() == null) ? 0 : getOrder_id().hashCode());
        result = prime * result + ((getStop_time() == null) ? 0 : getStop_time().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", order_id=").append(order_id);
        sb.append(", stop_time=").append(stop_time);
        sb.append("]");
        return sb.toString();
    }
}