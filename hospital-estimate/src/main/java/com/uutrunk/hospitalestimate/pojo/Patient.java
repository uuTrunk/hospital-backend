package com.uutrunk.hospitalestimate.pojo;

import com.uutrunk.hospitalestimate.Enum.AssessmentStatus;
import com.uutrunk.hospitalestimate.Enum.Gender;
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

}
    