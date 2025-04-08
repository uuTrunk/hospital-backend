package com.uutrunk.hospitalhealthdocument.dto;

import com.uutrunk.hospitalhealthdocument.dto.AdmissionHistoryDTO;
import com.uutrunk.hospitalhealthdocument.pojo.AdmissionHistory;
import com.uutrunk.hospitalhealthdocument.pojo.DiagnosisPlan;
import com.uutrunk.hospitalhealthdocument.pojo.HealthRecordMain;
import com.uutrunk.hospitalhealthdocument.pojo.PatientInfo;
import lombok.Data;

import java.util.List;

@Data
public class HealthRecordDetailDTO {
    private String recordId;
    private PatientDTO patientInfo;
    private List<AdmissionHistoryDTO> historyList;
    private List<DiagnosisPlanDTO> diagnosisList;

    // 使用构建器模式
    public static HealthRecordDetailDTO fromEntities(
        HealthRecordMain main,
        PatientInfo patient,
        List<AdmissionHistory> histories,
        List<DiagnosisPlan> plans) {
        HealthRecordDetailDTO dto = new HealthRecordDetailDTO();
        dto.setRecordId(main.getRecordId());
        dto.setPatientInfo(PatientDTO.fromEntity(patient));
        dto.setHistoryList(AdmissionHistoryDTO.listFromEntities(histories));
        dto.setDiagnosisList(DiagnosisPlanDTO.listFromEntities(plans));
        return dto;
    }
}