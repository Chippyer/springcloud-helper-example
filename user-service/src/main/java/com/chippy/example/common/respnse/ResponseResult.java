package com.chippy.example.common.respnse;

import com.ejoy.core.common.response.Result;

import java.io.Serializable;

/**
 * 系统通用返回类
 *
 * @author: chippy
 * @datetime 2020-12-15 11:06
 */
public class ResponseResult<T> implements Result<T>, Serializable {

    private int code;
    private String errorMsg;
    private T data;

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>();
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(data);
    }

    public static <T> ResponseResult<T> fail(int code, String errorMsg) {
        return new ResponseResult<>(code, errorMsg);
    }

    @Override
    public int definitionSuccessCode() {
        return 0;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @SuppressWarnings("unchecked")
    private ResponseResult() {
        this.code = this.definitionSuccessCode();
        this.data = (T)Boolean.TRUE;
        this.errorMsg = null;
    }

    private ResponseResult(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = null;
    }

    private ResponseResult(T data) {
        this.code = this.definitionSuccessCode();
        this.data = data;
        this.errorMsg = null;
    }

}
