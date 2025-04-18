package com.uutrunk.hospitalhealthdocument.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class HealthRecordMain {
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;
    @TableField("patient_id")
    private Integer patientId;
    @TableField("create_doctor_name")
    private String createDoctorName;
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    
    private Status status;

    public enum Status {
        待完善,
        已完善,
        已归档
    }
}