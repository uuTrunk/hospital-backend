package com.julien.medicalprescriptionservice.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("doctor_info")
public class DoctorInfo {

    @TableId("doctor_id")
    private Integer doctorId; // 医生ID

    @TableField("name")
    private String name; // 姓名

    @TableField("department")
    private String department; // 科室
}
