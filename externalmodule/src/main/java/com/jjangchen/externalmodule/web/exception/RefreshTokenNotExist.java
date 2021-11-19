package com.jjangchen.externalmodule.web.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RefreshTokenNotExist extends RuntimeException {
    RefreshTokenNotExist(String msg) {
        super(msg);
    }
}
