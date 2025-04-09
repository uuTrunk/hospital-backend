package com.uutrunk.hospitalestimate.service.Impl;

import com.uutrunk.hospitalestimate.service.CareAssessmentService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CareAssessmentServiceImpl implements CareAssessmentService {

    @Override
    public Map<String, Object> getCareAssessmentDetail(int assessmentId) {
        // 模拟从数据库或其他服务中获取数据
        Map<String, Object> detail = new HashMap<>();

        // 示例数据：生活自理能力
        Map<String, String> selfCareAbility = new HashMap<>();
        selfCareAbility.put("level", "正常");
        selfCareAbility.put("evaluation", "生活自理能力正常");

        // 示例数据：认知能力
        Map<String, String> cognitiveAbility = new HashMap<>();
        cognitiveAbility.put("level", "轻度");
        cognitiveAbility.put("evaluation", "认知能力轻度异常");

        // 构造返回结果
        detail.put("self_care_ability", selfCareAbility);
        detail.put("cognitive_ability", cognitiveAbility);

        return detail;
    }

    public void submitCareAssessment(int assessmentId, List<Map<String, String>> assessmentItems) {
        // 模拟保存逻辑
        for (Map<String, String> item : assessmentItems) {
            String project = item.get("project");
            String evaluation = item.get("evaluation");
            System.out.println("Saving assessment item - Project: " + project + ", Evaluation: " + evaluation);
        }
        // 实际实现中可以将数据保存到数据库
    }
}
