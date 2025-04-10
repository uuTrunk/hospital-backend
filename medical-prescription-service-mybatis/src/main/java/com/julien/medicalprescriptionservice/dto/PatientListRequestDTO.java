package com.julien.medicalprescriptionservice.dto;

import lombok.Data;

@Data
public class PatientListRequestDTO {

    private String nameOrNumber; // 姓名 / 入院编号模糊查询
    private Integer page;        // 页码
    private Integer pageSize;    // 每页数量
}
