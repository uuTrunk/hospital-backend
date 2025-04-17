package com.uutrunk.hospitalcommon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
@Accessors(chain = true)
public class DiagnosisPlanDTO {
    private Integer planId;
    private String diagnosis;
    private String treatmentPlan;
    private Integer recordDoctorName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;
}