package com.julien.hospitaldischargeservice.dto;

import com.julien.hospitaldischargeservice.entity.DischargeMain;
import com.julien.hospitaldischargeservice.entity.DischargeSummary;
import com.julien.hospitaldischargeservice.entity.DischargeHandover;
import com.julien.hospitaldischargeservice.entity.PatientInfo;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DischargeDTO {
    private Integer dischargeId;
    private PatientInfoDTO patientInfo;
    private String dischargeReason;
    private LocalDate dischargeDate;
    private String summaryStatus;
    private String handoverStatus;
    private DischargeSummaryDTO dischargeSummary;
    private List<DischargeHandoverDTO> dischargeHandovers;

    public static DischargeDTO fromEntity(DischargeMain entity) {
        if (entity == null) {
            return null;
        }

        DischargeDTO dto = new DischargeDTO();
        dto.setDischargeId(entity.getDischargeId());
        dto.setDischargeReason(entity.getDischargeReason().name());
        dto.setDischargeDate(entity.getDischargeDate());
        dto.setSummaryStatus(entity.getSummaryStatus().name());
        dto.setHandoverStatus(entity.getHandoverStatus().name());

        if (entity.getPatientInfo() != null) {
            dto.setPatientInfo(PatientInfoDTO.fromEntity(entity.getPatientInfo()));
        }

        if (entity.getDischargeSummary() != null) {
            dto.setDischargeSummary(DischargeSummaryDTO.fromEntity(entity.getDischargeSummary()));
        }

        if (entity.getDischargeHandovers() != null) {
            dto.setDischargeHandovers(entity.getDischargeHandovers().stream()
                    .map(DischargeHandoverDTO::fromEntity)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    @Data
    public static class PatientInfoDTO {
        private Integer patientId;
        private String name;
        private String gender;
        private String idNumber;
        private LocalDateTime registrationDate;
        private String bedNumber;
        private String careGrade;

        public static PatientInfoDTO fromEntity(PatientInfo entity) {
            if (entity == null) {
                return null;
            }

            PatientInfoDTO dto = new PatientInfoDTO();
            dto.setPatientId(entity.getPatientId());
            dto.setName(entity.getName());
            dto.setGender(entity.getGender().name());
            dto.setIdNumber(entity.getIdNumber());
            dto.setRegistrationDate(entity.getRegistrationDate());
            dto.setBedNumber(entity.getBedNumber());
            dto.setCareGrade(entity.getCareGrade().name());

            return dto;
        }
    }

    @Data
    public static class DischargeSummaryDTO {
        private Integer summaryId;
        private String summaryType;
        private String illnessName;
        private String admissionDiagnosis;
        private String inHospitalCondition;
        private String treatmentProcess;
        private String dischargeCondition;
        private String dischargeAdvice;
        private String rescueProcess;
        private String deathCause;
        private LocalDate dischargeDate;

        public static DischargeSummaryDTO fromEntity(DischargeSummary entity) {
            if (entity == null) {
                return null;
            }

            DischargeSummaryDTO dto = new DischargeSummaryDTO();
            dto.setSummaryId(entity.getSummaryId());
            dto.setSummaryType(entity.getSummaryType().name());
            dto.setIllnessName(entity.getIllnessName());
            dto.setAdmissionDiagnosis(entity.getAdmissionDiagnosis());
            dto.setInHospitalCondition(entity.getInHospitalCondition());
            dto.setTreatmentProcess(entity.getTreatmentProcess());
            dto.setDischargeCondition(entity.getDischargeCondition());
            dto.setDischargeAdvice(entity.getDischargeAdvice());
            dto.setRescueProcess(entity.getRescueProcess());
            dto.setDeathCause(entity.getDeathCause());
            dto.setDischargeDate(entity.getDischargeDate());

            return dto;
        }
    }

    @Data
    public static class DischargeHandoverDTO {
        private Integer handoverId;
        private String handoverItem;
        private String itemStatus;

        public static DischargeHandoverDTO fromEntity(DischargeHandover entity) {
            if (entity == null) {
                return null;
            }

            DischargeHandoverDTO dto = new DischargeHandoverDTO();
            dto.setHandoverId(entity.getHandoverId());
            dto.setHandoverItem(entity.getHandoverItem());
            dto.setItemStatus(entity.getItemStatus().name());

            return dto;
        }
    }
}