package com.jjangchen.externalmodule.web.support;

import com.google.common.collect.ImmutableMap;

import org.springframework.http.ResponseEntity;


public abstract class ApiRestSupport {
    protected static <T>ResponseEntity<?> response(T data) {
        return ResponseEntity.ok(
                ImmutableMap.of("response", data)
        );
    }
}
