package com.uutrunk.hospitalhealthdocument.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class PatientInfo {
    @TableId("patient_id")
    private Integer patientId;
    
    private String name;
    
    private String gender;
    
    private Integer age;
    
    private String idCard;
    
    private String admissionNumber;
    
    private LocalDateTime admissionDate;
    
    private String bedNumber;
    
    private String contactName;
    
    private String contactPhone;

    private CareGrade careGrade;

    public enum CareGrade {
        一级护理,
        二级护理,
        三级护理
    }
}