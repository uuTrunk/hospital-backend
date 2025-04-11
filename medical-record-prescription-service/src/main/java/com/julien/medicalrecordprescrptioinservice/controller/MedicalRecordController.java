package com.julien.medicalrecordprescrptioinservice.controller;

import com.julien.medicalrecordprescrptioinservice.dto.ApiResponse;
import com.julien.medicalrecordprescrptioinservice.dto.MedicalRecordCreateDTO;
import com.julien.medicalrecordprescrptioinservice.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medical-record")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping("/create")
    public ApiResponse<Object> createMedicalRecord(@RequestBody MedicalRecordCreateDTO dto) {
        try {
            String recordId = medicalRecordService.createMedicalRecord(dto);
            return new ApiResponse<>(200, "病历填写成功", new RecordResponse(recordId));
        } catch (Exception e) {
            return new ApiResponse<>(500, "病历填写失败", null);
        }
    }

    // 用来封装返回的recordId
    public static class RecordResponse {
        private String recordId;

        public RecordResponse(String recordId) {
            this.recordId = recordId;
        }

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }
    }
}
