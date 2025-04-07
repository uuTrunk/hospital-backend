package com.julien.medicalprescriptionservice.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("prescription_detail")
public class PrescriptionDetail {

    @TableId("detail_id")
    private Integer detailId; // 明细ID

    @TableField("prescription_id")
    private Integer prescriptionId; // 主表ID

    @TableField("drug_name")
    private String drugName; // 药品名称

    @TableField("specification")
    private String specification; // 规格

    @TableField("dosage")
    private String dosage; // 单次剂量

    @TableField("usage")
    private String usage; // 用法

    @TableField("frequency")
    private String frequency; // 频次

    @TableField("days")
    private Integer days; // 天数

    @TableField("total_quantity")
    private String totalQuantity; // 总量
}
