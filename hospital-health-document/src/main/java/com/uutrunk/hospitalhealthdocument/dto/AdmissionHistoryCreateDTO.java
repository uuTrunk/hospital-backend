// 补充参数校验
package com.uutrunk.hospitalhealthdocument.dto;

import com.uutrunk.hospitalhealthdocument.pojo.AdmissionHistory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdmissionHistoryCreateDTO {
    @NotBlank(message = "健康档案ID不能为空")
    private Integer recordId;

    @NotNull(message = "病史类型不能为空")
    private AdmissionHistory.HistoryType historyType;

    @NotBlank(message = "内容不能为空")
    private String content;
}