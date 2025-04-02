package com.uutrunk.hospitalestimate.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uutrunk.hospitalestimate.Enum.AssessmentStatus;
import com.uutrunk.hospitalestimate.POJO.AdmissionAssessment;
import com.uutrunk.hospitalestimate.POJO.Patient;
import com.uutrunk.hospitalestimate.Service.PatientService;
import com.uutrunk.hospitalestimate.VO.AdmissionAssessmentListRequest;
import com.uutrunk.hospitalestimate.VO.AdmissionAssessmentListResponse;
import com.uutrunk.hospitalestimate.mapper.AdmissionAssessmentMapper;
import com.uutrunk.hospitalestimate.Service.AdmissionAssessmentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@DubboService
public class AdmissionAssessmentServiceImpl implements AdmissionAssessmentService {

    @Autowired
    private AdmissionAssessmentMapper admissionAssessmentMapper;

    @Autowired
    private PatientService patientService;

    @Override
    public AdmissionAssessmentListResponse getAdmissionAssessmentList(AdmissionAssessmentListRequest request) {
        QueryWrapper<AdmissionAssessment> queryWrapper = new QueryWrapper<>();
        if (request.getStartDate() != null) {
            queryWrapper.ge("assessment_date", request.getStartDate());
        }
        if (request.getEndDate() != null) {
            queryWrapper.le("assessment_date", request.getEndDate());
        }
        if (request.getNameOrCode() != null) {
            queryWrapper.like("patient_id", request.getNameOrCode());
        }

        Page<AdmissionAssessment> page = new Page<>(request.getPage(), request.getPageSize());
        Page<AdmissionAssessment> resultPage = admissionAssessmentMapper.selectPage(page, queryWrapper);

        List<AdmissionAssessmentListResponse.AdmissionAssessmentItem> items = resultPage.getRecords().stream()
                .map(this::convertToItem)
                .collect(Collectors.toList());

        return new AdmissionAssessmentListResponse((int) resultPage.getTotal(), items);
    }

    private AdmissionAssessmentListResponse.AdmissionAssessmentItem convertToItem(AdmissionAssessment assessment) {
        // 创建返回对象
        AdmissionAssessmentListResponse.AdmissionAssessmentItem item = new AdmissionAssessmentListResponse.AdmissionAssessmentItem();

        // 设置患者ID
        if (assessment.getPatientId() != 0) {
            item.setPatientId(assessment.getPatientId());

            // 通过PatientService获取患者信息
            Patient patient = patientService.getPatientById(assessment.getPatientId());
            if (patient != null) {
                item.setName(patient.getName());
                item.setGender(patient.getGender());
                item.setRegistrationTime(patient.getRegistrationDate());
                item.setHealthStatus(patient.getHealthAssessmentStatus());
                item.setCareStatus(patient.getCareAssessmentStatus());
            } else {
                // 如果患者信息为空，设置默认值或记录日志
                item.setName(null);
                item.setGender(null);
                item.setRegistrationTime(null);
                item.setHealthStatus(null);
                item.setCareStatus(null);
            }
        } else {
            // 如果患者ID为空，设置默认值或记录日志
            item.setName(null);
            item.setGender(null);
            item.setRegistrationTime(null);
            item.setHealthStatus(null);
            item.setCareStatus(null);
        }

        // 设置登记时间，格式化日期
        if (assessment.getAssessmentDate() != null) {
            item.setRegistrationTime(assessment.getAssessmentDate());
        } else {
            item.setRegistrationTime(null);
        }

        // 设置健康状态和护理状态
        item.setHealthStatus(AssessmentStatus.valueOf("待评估"));
        item.setCareStatus(AssessmentStatus.valueOf("待评估"));

        return item;
    }

}
