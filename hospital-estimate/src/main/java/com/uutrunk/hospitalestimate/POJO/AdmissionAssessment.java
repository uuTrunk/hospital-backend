package com.uutrunk.hospitalestimate.POJO;

import lombok.Data;

import java.util.Date;

/**
 * @author uutrunk
 */
@Data
public class AdmissionAssessment {
    private int id;
    private int patientId;
    private int doctorId;
    private int nurseId;
    private Date assessmentDate;
}
