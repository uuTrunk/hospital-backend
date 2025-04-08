package com.julien.medicalprescriptionservice.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("medical_record")
public class MedicalRecord {

    @TableId("record_id")
    private String recordId; // 病历ID

    @TableField("patient_id")
    private Integer patientId; // 患者ID

    @TableField("doctor_id")
    private Integer doctorId; // 医生ID

    @TableField("visit_date")
    private LocalDateTime visitDate; // 就诊日期

    @TableField("chief_complaint")
    private String chiefComplaint; // 主诉

    @TableField("present_illness")
    private String presentIllness; // 现病史

    @TableField("past_illness")
    private String pastIllness; // 既往史

    @TableField("physical_exam")
    private String physicalExam; // 体检

    @TableField("auxiliary_exam")
    private String auxiliaryExam; // 辅助检查

    @TableField("treatment_opinion")
    private String treatmentOpinion; // 处理意见
}
