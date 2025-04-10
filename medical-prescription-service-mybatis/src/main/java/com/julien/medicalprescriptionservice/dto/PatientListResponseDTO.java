package com.julien.medicalprescriptionservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class PatientListResponseDTO {

    private Integer total; // 总记录数
    private List<PatientInfoDTO> list; // 患者列表

    @Data
    public static class PatientInfoDTO {
        private Integer patientId;       // 病人ID
        private String name;             // 姓名
        private String bedNumber;        // 床位号
        private String admissionNumber;  // 入院编号
    }
}
