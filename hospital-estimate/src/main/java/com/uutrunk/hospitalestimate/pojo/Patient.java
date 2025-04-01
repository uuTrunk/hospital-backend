package com.uutrunk.hospitalestimate.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author uutrunk
 */
@Data
public class Patient {

    private Integer patientId;
    private String name;
    private Gender gender;
    private String identificationCard;
    private Date registrationDate;
    private AssessmentStatus healthAssessmentStatus;
    private AssessmentStatus careAssessmentStatus;

    // Enum classes for Gender and AssessmentStatus
    public enum Gender {
        男, 女
    }

    public enum AssessmentStatus {
        待评估, 未完成, 完成
    }
}
    