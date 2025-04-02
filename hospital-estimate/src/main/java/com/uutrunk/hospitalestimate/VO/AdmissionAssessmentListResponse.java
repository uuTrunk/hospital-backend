package com.uutrunk.hospitalestimate.VO;

import com.uutrunk.hospitalestimate.Enum.AssessmentStatus;
import com.uutrunk.hospitalestimate.Enum.Gender;
import com.uutrunk.hospitalestimate.POJO.Patient;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AdmissionAssessmentListResponse {
    private int total;
    private List<AdmissionAssessmentItem> list;

    public AdmissionAssessmentListResponse(int total, List<AdmissionAssessmentItem> list) {
        this.total = total;
        this.list = list;
    }

    // Getters and Setters

    @Data
    public static class AdmissionAssessmentItem {
        private int patientId;
        private String name;
        private Gender gender;
        private Date registrationTime;
        private AssessmentStatus healthStatus;
        private AssessmentStatus careStatus;

        // Getters and Setters
    }
}
