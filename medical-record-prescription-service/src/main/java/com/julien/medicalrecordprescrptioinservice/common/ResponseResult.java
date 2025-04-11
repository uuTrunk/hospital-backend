package com.julien.medicalrecordprescrptioinservice.common;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ResponseResult<T> success(String message) {
        ResponseResult<T> r = new ResponseResult<>();
        r.setCode(200);
        r.setMessage(message);
        return r;
    }

    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> r = new ResponseResult<>();
        r.setCode(200);
        r.setMessage("成功");
        r.setData(data);
        return r;
    }

    public static <T> ResponseResult<T> fail(String message) {
        ResponseResult<T> r = new ResponseResult<>();
        r.setCode(500);
        r.setMessage(message);
        return r;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
