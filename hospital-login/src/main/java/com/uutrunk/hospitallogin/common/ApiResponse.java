package com.uutrunk.hospitallogin.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "成功", null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "成功", data);
    }

    public static ApiResponse<?> error(String message) {
        return new ApiResponse<>(0, message, null);
    }
}