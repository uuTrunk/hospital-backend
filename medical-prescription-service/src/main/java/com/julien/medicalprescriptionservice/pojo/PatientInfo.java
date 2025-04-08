package com.julien.medicalprescriptionservice.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("patient_info")
public class PatientInfo {

    @TableId("patient_id")
    private Integer patientId; // 主键

    @TableField("name")
    private String name; // 姓名

    @TableField("gender")
    private Gender gender; // 性别

    @TableField("age")
    private Integer age; // 年龄

    @TableField("bed_number")
    private String bedNumber; // 床位

    @TableField("admission_number")
    private String admissionNumber; // 入院编号

    @TableField("insurance_type")
    private String insuranceType; // 费别

    @TableField("insurance_id")
    private String insuranceId; // 医保卡号

    public enum Gender {
        男, 女
    }
}
