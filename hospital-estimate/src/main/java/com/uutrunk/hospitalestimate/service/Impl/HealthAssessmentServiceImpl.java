package com.uutrunk.hospitalestimate.service.Impl;

import com.uutrunk.hospitalestimate.mapper.CurrentIllnessMapper;
import com.uutrunk.hospitalestimate.mapper.DietRestrictionMapper;
import com.uutrunk.hospitalestimate.mapper.PatientAssessmentMapper;
import com.uutrunk.hospitalestimate.Enum.AdmissionAgreement;
import com.uutrunk.hospitalestimate.pojo.CurrentIllness;
import com.uutrunk.hospitalestimate.pojo.DietRestriction;
import com.uutrunk.hospitalestimate.pojo.PatientAssessment;
import com.uutrunk.hospitalestimate.service.HealthAssessmentService;
import com.uutrunk.hospitalestimate.vo.HealthAssessmentVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
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
