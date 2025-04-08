package com.julien.medicalprescriptionservice.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("prescription_main")
public class PrescriptionMain {

    @TableId("prescription_id")
    private String prescriptionId; // 处方ID

    @TableField("patient_id")
    private Integer patientId; // 患者ID

    @TableField("doctor_id")
    private Integer doctorId; // 医生ID

    @TableField("diagnosis")
    private String diagnosis; // 临床诊断

    @TableField("prescription_date")
    private LocalDateTime prescriptionDate = LocalDateTime.now(); // 开方时间

    @TableField("status")
    private PrescriptionStatus status; // 发药状态

    public enum PrescriptionStatus {
        未发药, 已发药
    }
}
