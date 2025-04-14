package com.uutrunk.hospitalhealthdocument.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uutrunk.hospitalhealthdocument.pojo.HealthRecordMain;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class HealthRecordDTO {
    private Integer recordId;
    private String patientName;
    private String createDoctorName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private HealthRecordMain.Status status;

    public static HealthRecordDTO fromEntity(HealthRecordMain entity) {
        return new HealthRecordDTO()
            .setRecordId(entity.getRecordId())
            .setCreateDoctorName(entity.getCreateDoctorName()) // 需要补充医生信息关联
            .setCreateTime(entity.getCreateTime())
            .setStatus(entity.getStatus());
    }
}