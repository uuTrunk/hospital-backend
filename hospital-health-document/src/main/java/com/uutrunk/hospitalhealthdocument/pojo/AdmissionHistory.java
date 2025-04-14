package com.uutrunk.hospitalhealthdocument.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class AdmissionHistory {
    @TableId(value = "history_id", type = IdType.AUTO)
    private Integer historyId;
    
    private String recordId;
    
    private HistoryType historyType;
    
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;

    public enum HistoryType {
        现病史,
        既往史
    }
}