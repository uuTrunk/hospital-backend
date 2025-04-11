package com.uutrunk.hospitalordermanagement.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PatientInfo {
    @TableId(value = "patient_id", type = IdType.AUTO)
    private Integer patientId;
    private String name;
    private Gender gender;
    @TableField(value = "id_card")
    private String idCard;
    @TableField(value = "birth_date")
    private Date birthDate;
    @TableField(value = "registration_date")
    private LocalDateTime registrationDate;
    @TableField(value = "bed_number")
    private String bedNumber;
    @TableField(value = "care_grade")
    private CareGrade careGrade;
    @TableField(value = "admission_number")
    private String admissionNumber;
    @TableField(value = "insurance_type")
    private String insuranceType;
    @TableField(value = "insurance_id")
    private String insuranceId;
    @TableField(value = "contact_phone")
    private String contactName;
    @TableField(value = "contact_phone")
    private String contactPhone;

    public enum Gender {
        男,
        女
    }

    public enum CareGrade {
        一级护理,
        二级护理,
        三级护理
    }
}