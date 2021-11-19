package com.jjangchen.externalmodule.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class JwtObject {
    private String accessToken;

    private String refreshToken;
}
