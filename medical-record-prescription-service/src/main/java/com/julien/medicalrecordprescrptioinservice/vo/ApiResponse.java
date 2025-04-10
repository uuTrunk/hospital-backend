package com.julien.medicalrecordprescrptioinservice.vo;

import lombok.Data;

@Data
public class ApiResponse {
    private int code;
    private String message;
    private Data data;

    public ApiResponse() {}

    public ApiResponse(int code, String message, Data data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse success(Data data) {
        return new ApiResponse(200, "成功", data);
    }

    public static ApiResponse failure(int code, String message) {
        return new ApiResponse(code, message, null);
    }

    @lombok.Data
    public static class Data {
        private long total;
        private Object list;

        public Data() {}

        public Data(long total, Object list) {
            this.total = total;
            this.list = list;
        }
    }
}
