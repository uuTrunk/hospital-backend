package com.uutrunk.hospitalordermanagement.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "成功", data);
    }

    public static ApiResponse<?> error(String message) {
        return new ApiResponse<>(0, message, null);
    }
}