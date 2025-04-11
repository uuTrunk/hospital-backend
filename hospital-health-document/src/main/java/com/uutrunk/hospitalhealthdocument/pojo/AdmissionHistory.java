package com.uutrunk.hospitalhealthdocument.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class AdmissionHistory {
    @TableId(value = "history_id", type = IdType.AUTO)
    private Integer historyId;
    
    private String recordId;
    
    private Integer typeId;
    
    private String content;
    
    private LocalDateTime recordTime;
}