package com.jjangchen.externalmodule.web.exception;

public class AccountNotFoundException extends RuntimeException {
    AccountNotFoundException(String msg) {
        super(msg);
    }
}
