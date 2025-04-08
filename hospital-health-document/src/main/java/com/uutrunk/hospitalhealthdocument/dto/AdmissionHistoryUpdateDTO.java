// 补充参数校验
package com.uutrunk.hospitalhealthdocument.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AdmissionHistoryUpdateDTO {
    @NotNull(message = "病史ID不能为空")
    private Integer historyId;

    @NotBlank(message = "内容不能为空")
    private String content;
}