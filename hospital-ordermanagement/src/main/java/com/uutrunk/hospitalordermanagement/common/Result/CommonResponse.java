package com.uutrunk.hospitalordermanagement.common.Result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonResponse<T> {
    private static final int SUCCESS = 200;
    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 请求状态码 正常-0，异常-非0
     */
    private int code;

    /**
     * 描述信息，当返回码不为0时，表示错误信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public static <T> CommonResponse<T> buildSuccess() {
        return new CommonResponse<T>(true, SUCCESS, "OK", null);
    }

    public static <T> CommonResponse<T> buildSuccess(T data) {
        return new CommonResponse<T>(true, SUCCESS, "OK", data);
    }
}
