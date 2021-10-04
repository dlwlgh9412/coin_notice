package com.jjangchen.api_module.web.support;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.jjangchen.api_module.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public abstract class ApiRestSupport {
    protected static <T>ResponseEntity<?> response(T data) {
        return ResponseEntity.ok(
                ImmutableMap.of(
                        "code", "200",
                        "data", data != null ? data : new JsonObject()
                )
        );
    }
}
