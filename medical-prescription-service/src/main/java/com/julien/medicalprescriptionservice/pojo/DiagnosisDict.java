package com.julien.medicalprescriptionservice.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 诊断字典实体类，对应数据库表 diagnosis_dict
 */
@Data
@TableName("diagnosis_dict")
public class DiagnosisDict {

    /**
     * 诊断 ID（自增）
     */
    @TableId(value = "diagnosis_id")
    private Integer diagnosisId;

    /**
     * 诊断名称（如高血压、上呼吸道感染）
     */
    @TableField("diagnosis_name")
    private String diagnosisName;
}
