package com.uutrunk.hospitalhealthdocument.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uutrunk.hospitalhealthdocument.pojo.AdmissionHistory;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class AdmissionHistoryDTO {
    private Integer historyId;
    private AdmissionHistory.HistoryType historyType; // 需要字典转换
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;

    public static AdmissionHistoryDTO fromEntity(AdmissionHistory entity) {
        return new AdmissionHistoryDTO()
            .setHistoryId(entity.getHistoryId())
            .setContent(entity.getContent())
            .setRecordTime(entity.getRecordTime());
            // 需要补充类型转换（typeId转为类型名称）
    }

    public static List<AdmissionHistoryDTO> listFromEntities(List<AdmissionHistory> entities) {
        return entities.stream()
            .map(AdmissionHistoryDTO::fromEntity)
            .collect(Collectors.toList());
    }
}