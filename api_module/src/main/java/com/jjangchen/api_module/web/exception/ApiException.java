package com.jjangchen.api_module.web.exception;

public class ApiException extends RuntimeException {
    String code;

    public String getCode() {
        return code;
    }

    public ApiException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
