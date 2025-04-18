package com.uutrunk.hospitalhealthdocument.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uutrunk.hospitalhealthdocument.enums.HistoryType;
import com.uutrunk.hospitalhealthdocument.pojo.AdmissionHistory;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class AdmissionHistoryDTO implements Serializable {
    private static final long serialVersionUID = 3L;
    private Integer historyId;
    private HistoryType historyType; // 需要字典转换
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;
}