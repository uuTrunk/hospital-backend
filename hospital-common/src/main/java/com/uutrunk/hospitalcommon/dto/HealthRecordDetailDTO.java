package com.uutrunk.hospitalcommon.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class HealthRecordDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer recordId;
    private PatientDTO patientInfo;          // 确保该类实现 Serializable
    private List<AdmissionHistoryDTO> historyList;  // 确保该类实现 Serializable
    private List<DiagnosisPlanDTO> diagnosisList;   // 确保该类实现 Serializable
}
