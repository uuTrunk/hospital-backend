package com.uutrunk.hospitalestimate.Service;

import com.uutrunk.hospitalestimate.VO.HealthAssessmentVO;

/**
 * @author uutrunk
 */
public interface HealthAssessmentService {
    HealthAssessmentVO getDetail(int assessmentId);
    void submit(HealthAssessmentVO healthAssessmentVO);
}
