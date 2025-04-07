package com.julien.medicalprescriptionservice.dto;

public class PatientQueryDTO {

    private String nameOrNumber;  // 姓名或入院编号
    private Integer page;         // 页码
    private Integer pageSize;     // 每页显示数量

    // Getters and Setters
    public String getNameOrNumber() {
        return nameOrNumber;
    }

    public void setNameOrNumber(String nameOrNumber) {
        this.nameOrNumber = nameOrNumber;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
