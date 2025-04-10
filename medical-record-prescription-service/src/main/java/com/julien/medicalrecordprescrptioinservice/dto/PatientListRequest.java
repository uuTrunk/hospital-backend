package com.julien.medicalrecordprescrptioinservice.dto;

public class PatientListRequest {
    private String nameOrNumber; // 姓名或入院编号模糊查询
    private int page;
    private int pageSize;

    // Getter for nameOrNumber
    public String getNameOrNumber() {
        return nameOrNumber;
    }

    // Setter for nameOrNumber
    public void setNameOrNumber(String nameOrNumber) {
        this.nameOrNumber = nameOrNumber;
    }

    // Getter for page
    public int getPage() {
        return page;
    }

    // Setter for page
    public void setPage(int page) {
        this.page = page;
    }

    // Getter for pageSize
    public int getPageSize() {
        return pageSize;
    }

    // Setter for pageSize
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
