package com.uutrunk.hospitalestimate.service;

import java.util.List;
import java.util.Map;

public interface CareAssessmentService {
    public Map<String, Object> getCareAssessmentDetail(int assessmentId);
    public void submitCareAssessment(int assessmentId, List<Map<String, String>> assessmentItems);
}
