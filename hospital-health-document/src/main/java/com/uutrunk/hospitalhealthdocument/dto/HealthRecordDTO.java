package com.uutrunk.hospitalhealthdocument.dto;

import com.uutrunk.hospitalhealthdocument.pojo.HealthRecordMain;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class HealthRecordDTO {
    private String recordId;
    private String patientName;
    private String createDoctorName;
    private LocalDateTime createTime;
    private HealthRecordMain.Status recordStatus;

    public static HealthRecordDTO fromEntity(HealthRecordMain entity) {
        return new HealthRecordDTO()
            .setRecordId(entity.getRecordId())
            .setCreateDoctorName(entity.getCreateDoctorName()) // 需要补充医生信息关联
            .setCreateTime(entity.getCreateTime())
            .setRecordStatus(entity.getStatus());
    }
}