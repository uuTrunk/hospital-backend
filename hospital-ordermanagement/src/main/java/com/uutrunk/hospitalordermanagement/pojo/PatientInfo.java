package com.uutrunk.hospitalordermanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PatientInfo {
    @TableId(value = "patient_id", type = IdType.AUTO)
    private Integer patientId;
    private String name;
    private String idNumber;
    private String bedNumber;
}