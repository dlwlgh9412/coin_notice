package com.jjangchen.externalmodule.web.support;

import com.google.common.collect.ImmutableMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public abstract class ApiRestSupport {
    protected static <T>ResponseEntity<?> response(T data) {
        if(data == null) {
            return new ResponseEntity<>(ImmutableMap.of("response", data), HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(
                ImmutableMap.of("response", data)
        );
    }
}
