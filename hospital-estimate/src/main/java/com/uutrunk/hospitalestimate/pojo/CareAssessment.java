package com.uutrunk.hospitalestimate.pojo;

import com.uutrunk.hospitalestimate.Enum.RiskLevel;
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
}
