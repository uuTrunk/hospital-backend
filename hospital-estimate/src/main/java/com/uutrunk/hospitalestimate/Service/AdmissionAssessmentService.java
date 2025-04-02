package com.uutrunk.hospitalestimate.Service;

import com.uutrunk.hospitalestimate.VO.AdmissionAssessmentListRequest;
import com.uutrunk.hospitalestimate.VO.AdmissionAssessmentListResponse;

public interface AdmissionAssessmentService {
    AdmissionAssessmentListResponse getAdmissionAssessmentList(AdmissionAssessmentListRequest request);
}
