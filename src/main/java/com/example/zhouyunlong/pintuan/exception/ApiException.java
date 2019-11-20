package com.example.zhouyunlong.pintuan.exception;

import com.example.zhouyunlong.pintuan.api.IErrorCode;

/**
 * 2 * @Author: zhouyunlong
 * 3 * @Date: 2019/11/17 10:03
 * 4
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = -5885155226898287919L;
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return this.errorCode;
    }
}