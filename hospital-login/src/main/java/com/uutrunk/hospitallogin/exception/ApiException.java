package com.uutrunk.hospitallogin.exception;

import org.apache.dubbo.remoting.http12.HttpStatus;

// 补充自定义异常类（可选）
public class ApiException extends RuntimeException {
    private final HttpStatus status;

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}