package com.uutrunk.hospitalhealthdocument.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class AdmissionHistory {
    @Id
    private Integer historyId;
    
    private String recordId;
    
    private Integer typeId;
    
    private String content;
    
    private LocalDateTime recordTime;
}