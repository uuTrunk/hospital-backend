package com.julien.medicalprescriptionservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class PatientListResponse {

    private long total; // 总记录数
    private List<PatientInfoResponse> list; // 患者列表
}


