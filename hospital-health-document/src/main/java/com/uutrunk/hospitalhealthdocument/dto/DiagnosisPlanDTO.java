package com.uutrunk.hospitalhealthdocument.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uutrunk.hospitalhealthdocument.pojo.DiagnosisPlan;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class DiagnosisPlanDTO implements Serializable {
    private static final long serialVersionUID = 4L;
    private Integer planId;
    private String diagnosis;
    private String treatmentPlan;
    private Integer recordDoctorName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;
}