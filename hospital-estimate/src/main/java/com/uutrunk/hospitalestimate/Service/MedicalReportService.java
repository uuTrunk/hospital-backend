package com.uutrunk.hospitalestimate.Service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface MedicalReportService {
    /**
     * 上传检查报告文件
     *
     * @param assessmentId 评估ID
     * @param reportType   报告类型
     * @param file         上传的文件
     * @return 包含文件路径的Map
     * @throws Exception 如果上传失败
     */
    Map<String, Object> uploadMedicalReport(int assessmentId, String reportType, MultipartFile file) throws Exception;
}
