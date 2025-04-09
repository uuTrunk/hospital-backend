package com.uutrunk.hospitalestimate.pojo;

import lombok.Data;

/**
 * @author uutrunk
 */
@Data
public class MedicalReport {
    private int id;
    private int assessmentId;
    private String filePath;
    private FileFormat fileFormat;
    private ReportType reportType;

    private enum FileFormat {
        jpg,
        png
    }
    private enum ReportType {
        生化检验,
        X光,
        超声检查,
        心电图,
        CT,
        其他
    }
}
