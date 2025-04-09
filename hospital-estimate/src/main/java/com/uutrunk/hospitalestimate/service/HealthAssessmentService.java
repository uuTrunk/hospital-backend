package com.uutrunk.hospitalestimate.service;

import com.uutrunk.hospitalestimate.vo.HealthAssessmentVO;

/**
 * @author uutrunk
 */
public interface HealthAssessmentService {
    HealthAssessmentVO getDetail(int assessmentId);
    void submit(HealthAssessmentVO healthAssessmentVO);
}
