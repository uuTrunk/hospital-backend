package com.uutrunk.hospitalhealthdocument.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.uutrunk.hospitalhealthdocument.enums.HistoryType;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class AdmissionHistory {
    @TableId(value = "history_id", type = IdType.AUTO)
    private Integer historyId;
    
    private Integer recordId;
    
    private HistoryType historyType;
    
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;
}