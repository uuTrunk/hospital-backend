package com.uutrunk.hospitalhealthdocument.dto;

import com.uutrunk.hospitalhealthdocument.pojo.DiagnosisPlan;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class DiagnosisPlanDTO {
    private Integer planId;
    private String diagnosisContent;
    private Integer recordDoctorId;
    private LocalDateTime recordTime;

    public static DiagnosisPlanDTO fromEntity(DiagnosisPlan entity) {
        return new DiagnosisPlanDTO()
            .setPlanId(entity.getPlanId())
            .setDiagnosisContent(entity.getTreatmentPlan())
            .setRecordDoctorId(entity.getRecordDoctorId()) // 需要补充医生信息
            .setRecordTime(entity.getRecordTime());
    }

    public static List<DiagnosisPlanDTO> listFromEntities(List<DiagnosisPlan> entities) {
        return entities.stream()
            .map(DiagnosisPlanDTO::fromEntity)
            .collect(Collectors.toList());
    }
}