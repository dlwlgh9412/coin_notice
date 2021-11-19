package com.jjangchen.common.exception;

public class AccessTokenExpiresException extends RuntimeException {
    public AccessTokenExpiresException(String msg) {
        super(msg);
    }
}
