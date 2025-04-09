package com.uutrunk.hospitalestimate.service;

import com.uutrunk.hospitalestimate.vo.AdmissionAssessmentListRequest;
import com.uutrunk.hospitalestimate.vo.AdmissionAssessmentListResponse;

public interface AdmissionAssessmentService {
    AdmissionAssessmentListResponse getAdmissionAssessmentList(AdmissionAssessmentListRequest request);
}
