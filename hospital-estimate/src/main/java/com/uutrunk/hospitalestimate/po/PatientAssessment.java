package com.uutrunk.hospitalestimate.po;

import java.time.LocalDateTime;

/**
 * @author uutrunk
 */
public class PatientAssessment {
    private int id;
    private String forbiddenMedicines;
    private String physicalConclusion;
    private AdmissionAgreement admissionAgreement;
    private LocalDateTime createTime;

    private enum AdmissionAgreement {
        同意住院, 拒绝住院
    }
}
