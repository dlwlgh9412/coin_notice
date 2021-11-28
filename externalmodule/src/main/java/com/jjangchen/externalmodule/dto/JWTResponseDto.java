package com.jjangchen.externalmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class JWTResponseDto {
    private String accessToken;
    private String refreshToken;
}
