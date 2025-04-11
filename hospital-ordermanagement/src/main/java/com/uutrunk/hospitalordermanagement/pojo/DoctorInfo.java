package com.uutrunk.hospitalordermanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class DoctorInfo {
    @TableId(value = "doctor_id", type = IdType.AUTO)
    private Integer doctorId;
    private String name;
    private String department;
}