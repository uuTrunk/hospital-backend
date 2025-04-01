package com.uutrunk.hospitalestimate.VO;

import com.uutrunk.hospitalestimate.Enum.AdmissionAgreement;
import lombok.Data;

import java.util.List;

/**
 * @author uutrunk
 */
@Data
public class HealthAssessmentVO {
    private int assessmentId;
    private List<String> currentIllness;
    private String forbiddenMedicines;
    private List<String> dietRestrictions;
    private String physicalConclusion;
    private AdmissionAgreement admissionAgreement;
}
