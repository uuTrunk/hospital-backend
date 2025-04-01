package com.uutrunk.hospitalestimate.Service.Impl;

import com.uutrunk.hospitalestimate.DAO.CurrentIllnessMapper;
import com.uutrunk.hospitalestimate.DAO.DietRestrictionMapper;
import com.uutrunk.hospitalestimate.DAO.PatientAssessmentMapper;
import com.uutrunk.hospitalestimate.Enum.AdmissionAgreement;
import com.uutrunk.hospitalestimate.POJO.CurrentIllness;
import com.uutrunk.hospitalestimate.POJO.DietRestriction;
import com.uutrunk.hospitalestimate.POJO.PatientAssessment;
import com.uutrunk.hospitalestimate.Service.HealthAssessmentService;
import com.uutrunk.hospitalestimate.VO.HealthAssessmentVO;

import java.time.LocalDateTime;
import java.util.List;

public class HealthAssessmentServiceImpl implements HealthAssessmentService {

    private CurrentIllnessMapper currentIllnessMapper;
    private PatientAssessmentMapper patientAssessmentMapper;
    private DietRestrictionMapper dietRestrictionMapper;

    public HealthAssessmentServiceImpl(PatientAssessmentMapper patientAssessmentMapper,
                                       DietRestrictionMapper dietRestrictionMapper,
                                       CurrentIllnessMapper currentIllnessMapper ) {
        this.patientAssessmentMapper = patientAssessmentMapper;
        this.dietRestrictionMapper = dietRestrictionMapper;
        this.currentIllnessMapper = currentIllnessMapper;
    }

    @Override
    public HealthAssessmentVO getDetail(int assessmentId) {
//        QueryWrapper<CurrentIllness> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("assessmentId", assessmentId);
        try{
            CurrentIllness currentIllness = currentIllnessMapper.selectById(assessmentId);
            PatientAssessment patientAssessment = patientAssessmentMapper.selectById(assessmentId);
            DietRestriction dietRestriction = dietRestrictionMapper.selectById(assessmentId);
            HealthAssessmentVO healthAssessmentVO = new HealthAssessmentVO();
            healthAssessmentVO.setAssessmentId(assessmentId);
            healthAssessmentVO.setCurrentIllness(currentIllness.getIllnessName());
            healthAssessmentVO.setForbiddenMedicines(patientAssessment.getForbiddenMedicines());
            healthAssessmentVO.setDietRestrictions(dietRestriction.getRestrictionType());
            healthAssessmentVO.setPhysicalConclusion(patientAssessment.getPhysicalConclusion());
            healthAssessmentVO.setAdmissionAgreement(patientAssessment.getAdmissionAgreement());
            return healthAssessmentVO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void submit(HealthAssessmentVO healthAssessmentVO) {
        int assessmentId = healthAssessmentVO.getAssessmentId();
        List<String> illnessName = healthAssessmentVO.getCurrentIllness();
        String forbiddenMedicines = healthAssessmentVO.getForbiddenMedicines();
        List<String> dietRestrictions = healthAssessmentVO.getDietRestrictions();
        String physicalConclusion = healthAssessmentVO.getPhysicalConclusion();
        AdmissionAgreement admissionAgreement = healthAssessmentVO.getAdmissionAgreement();

        CurrentIllness currentIllness = new CurrentIllness();
        currentIllness.setAssessmentId(assessmentId);
        currentIllness.setIllnessName(illnessName);
        currentIllnessMapper.insert(currentIllness);

        PatientAssessment patientAssessment = new PatientAssessment();
        patientAssessment.setId(assessmentId);
        patientAssessment.setForbiddenMedicines(forbiddenMedicines);
        patientAssessment.setPhysicalConclusion(physicalConclusion);
        patientAssessment.setAdmissionAgreement(admissionAgreement);
        patientAssessment.setCreateTime(LocalDateTime.now());
        patientAssessmentMapper.insert(patientAssessment);

        DietRestriction dietRestriction = new DietRestriction();
        dietRestriction.setAssessmentId(assessmentId);
        dietRestriction.setRestrictionType(dietRestrictions);
        dietRestrictionMapper.insert(dietRestriction);

        return;

    }
}
