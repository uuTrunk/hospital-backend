package com.uutrunk.hospitalestimate.service.Impl;

import com.uutrunk.hospitalestimate.exception.UploadException;
import com.uutrunk.hospitalestimate.service.MedicalReportService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MedicalReportServiceImpl implements MedicalReportService {

    private static final String UPLOAD_DIR = "uploads/reports/";

    @Transactional
    @Override
    public Map<String, Object> uploadMedicalReport(int assessmentId, String reportType, MultipartFile file) throws FileUploadException {
        // 检查文件是否为空
        if (file.isEmpty()) {
            throw new UploadException("上传的文件不能为空");
        }

        // 创建上传目录（如果不存在）
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 获取文件扩展名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.lastIndexOf(".") == -1) {
            throw new UploadException("文件格式无效");
        }
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        if (!extension.equals(".jpg") && !extension.equals(".png")) {
            throw new UploadException("仅支持JPG/PNG格式文件");
        }

        // 生成文件名
        String newFilename = reportType.toLowerCase().replaceAll("\\s+", "_") + "_" +
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;

        // 构造文件存储路径
        String filePath = UPLOAD_DIR + newFilename;

        // 保存文件
        Path path = Paths.get(filePath);
        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new FileUploadException("文件保存失败: " + e.getMessage());
        }

        // 构造返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("file_path", filePath);
        return result;
    }
}
