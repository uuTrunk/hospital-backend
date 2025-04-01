package com.uutrunk.hospitalestimate.POJO;

import lombok.Data;

/**
 * @author uutrunk
 */
@Data
public class CareAssessment {
    private int id;
    private int assessmentId;
    private String careAssessmentProject;
    private String careEvaluation;
    private RiskLevel riskLevel;

    private enum RiskLevel {
        正常,
        轻度,
        中毒,
        重度
    }
}
