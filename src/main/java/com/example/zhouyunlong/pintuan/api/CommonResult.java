package com.example.zhouyunlong.pintuan.api;

import com.example.zhouyunlong.pintuan.exception.ApiException;
import com.example.zhouyunlong.pintuan.enums.ApiErrorCode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Optional;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/17 9:46
 * 4
 */
@Data
@Accessors(chain = true)
public class CommonResult<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 业务错误码
     */
    private long code;
    /**
     * 结果集
     */
    private T data;
    /**
     * 描述
     */
    private String msg;

    public CommonResult() {
        // to do nothing
    }

    public CommonResult(IErrorCode errorCode) {
        errorCode = Optional.ofNullable(errorCode).orElse(ApiErrorCode.FAILED);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> CommonResult<T> ok(T data) {
        ApiErrorCode aec = ApiErrorCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = ApiErrorCode.FAILED;
        }
        return restResult(data, aec);
    }

    public static <T> CommonResult<T> failed(String msg) {
        return restResult(null, ApiErrorCode.FAILED.getCode(), msg);
    }

    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return restResult(null, errorCode);
    }

    public static <T> CommonResult<T> restResult(T data, IErrorCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> CommonResult<T> restResult(T data, long code, String msg) {
        CommonResult<T> apiResult = new CommonResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public boolean ok() {
        return ApiErrorCode.SUCCESS.getCode() == code;
    }

    /**
     * 服务间调用非业务正常，异常直接释放
     */
    public T serviceData() {
        if (!ok()) {
            throw new ApiException(this.msg);
        }
        return data;
    }
}
