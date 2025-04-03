package com.uutrunk.hospitalordermanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 医嘱操作记录表
 * @TableName order_operation_log
 */
@TableName(value ="order_operation_log")
@Data
public class OrderOperationLog {
    /**
     * 记录 ID，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer log_id;

    /**
     * 关联医嘱 ID
     */
    private String order_id;

    /**
     * 操作类型（作废、校对等）
     */
    private String operation_type;

    /**
     * 操作时间
     */
    private Date operation_time;

    /**
     * 操作人 ID（医生 / 护士）
     */
    private Integer operator_id;

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
        OrderOperationLog other = (OrderOperationLog) that;
        return (this.getLog_id() == null ? other.getLog_id() == null : this.getLog_id().equals(other.getLog_id()))
            && (this.getOrder_id() == null ? other.getOrder_id() == null : this.getOrder_id().equals(other.getOrder_id()))
            && (this.getOperation_type() == null ? other.getOperation_type() == null : this.getOperation_type().equals(other.getOperation_type()))
            && (this.getOperation_time() == null ? other.getOperation_time() == null : this.getOperation_time().equals(other.getOperation_time()))
            && (this.getOperator_id() == null ? other.getOperator_id() == null : this.getOperator_id().equals(other.getOperator_id()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLog_id() == null) ? 0 : getLog_id().hashCode());
        result = prime * result + ((getOrder_id() == null) ? 0 : getOrder_id().hashCode());
        result = prime * result + ((getOperation_type() == null) ? 0 : getOperation_type().hashCode());
        result = prime * result + ((getOperation_time() == null) ? 0 : getOperation_time().hashCode());
        result = prime * result + ((getOperator_id() == null) ? 0 : getOperator_id().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", log_id=").append(log_id);
        sb.append(", order_id=").append(order_id);
        sb.append(", operation_type=").append(operation_type);
        sb.append(", operation_time=").append(operation_time);
        sb.append(", operator_id=").append(operator_id);
        sb.append("]");
        return sb.toString();
    }
}