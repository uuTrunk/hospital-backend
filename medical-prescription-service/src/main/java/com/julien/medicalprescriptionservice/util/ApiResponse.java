package com.julien.medicalprescriptionservice.util;

public class ApiResponse {

    private int code;
    private String message;
    private Data data;

    public ApiResponse(int code, String message, Data data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters

    public static class Data<T> {
        private long total;
        private T list;

        public Data(long total, T list) {
            this.total = total;
            this.list = list;
        }

        // Getters and Setters
    }
}
