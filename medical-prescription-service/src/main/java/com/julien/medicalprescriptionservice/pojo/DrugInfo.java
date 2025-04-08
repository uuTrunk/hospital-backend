package com.julien.medicalprescriptionservice.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("drug_info")
public class DrugInfo {

    @TableId("drug_id")
    private Integer drugId; // 药品ID

    @TableField("drug_name")
    private String drugName; // 药品名称

    @TableField("specification")
    private String specification; // 规格

    @TableField("contraindications")
    private String contraindications; // 禁忌
}
