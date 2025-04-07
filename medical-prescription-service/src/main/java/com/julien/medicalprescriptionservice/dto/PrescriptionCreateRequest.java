package com.julien.medicalprescriptionservice.dto;

import java.util.List;
import lombok.Data;

@Data
public class PrescriptionCreateRequest {

    private Integer patientId;
    private Integer doctorId;
    private String diagnosis;
    private List<PrescriptionDetailRequest> prescriptionDetail;

    @Data
    public static class PrescriptionDetailRequest {
        private String drugName;
        private String specification;
        private String dosage;
        private String usage;
        private String frequency;
        private Integer days;
        private String totalQuantity;
    }
}
