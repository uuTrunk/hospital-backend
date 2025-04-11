package com.uutrunk.hospitalhealthdocument.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String diagnosis;
    private String treatmentPlan;
    private Integer recordDoctorName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;

    public static DiagnosisPlanDTO fromEntity(DiagnosisPlan entity) {
        return new DiagnosisPlanDTO()
            .setPlanId(entity.getPlanId())
            .setTreatmentPlan(entity.getTreatmentPlan())
            .setRecordDoctorName(entity.getRecordDoctorName()); // 需要补充医生信息
    }

    public static List<DiagnosisPlanDTO> listFromEntities(List<DiagnosisPlan> entities) {
        return entities.stream()
            .map(DiagnosisPlanDTO::fromEntity)
            .collect(Collectors.toList());
    }
}