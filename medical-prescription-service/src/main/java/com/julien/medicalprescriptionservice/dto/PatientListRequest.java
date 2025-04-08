package com.julien.medicalprescriptionservice.dto;

import lombok.Data;

@Data
public class PatientListRequest {

    private String nameOrNumber; // 姓名 / 入院编号模糊查询
    private int page;            // 页码
    private int pageSize;        // 每页数量
}
