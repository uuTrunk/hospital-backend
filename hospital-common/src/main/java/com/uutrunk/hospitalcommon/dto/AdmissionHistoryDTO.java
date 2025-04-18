package com.uutrunk.hospitalcommon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uutrunk.hospitalcommon.enums.HistoryType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
@Accessors(chain = true)
public class AdmissionHistoryDTO {
    private Integer historyId;
    private HistoryType historyType; // 需要字典转换
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;
}