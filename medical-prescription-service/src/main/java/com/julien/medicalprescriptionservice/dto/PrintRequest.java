package com.julien.medicalprescriptionservice.dto;

import lombok.Data;

@Data
public class PrintRequest {

    private String recordId;       // 病历 ID（优先）
    private String prescriptionId; // 处方 ID
}
