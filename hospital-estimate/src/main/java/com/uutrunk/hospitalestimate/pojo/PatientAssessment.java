package com.uutrunk.hospitalestimate.pojo;

import com.uutrunk.hospitalestimate.Enum.AdmissionAgreement;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author uutrunk
 */
@Data
public class PatientAssessment {
    private int id;
    private String forbiddenMedicines;
    private String physicalConclusion;
    private AdmissionAgreement admissionAgreement;
    private LocalDateTime createTime;
}
