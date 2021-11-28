package com.jjangchen.externalmodule.client.kakao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoTokenRequest {
    private String grantType = "authorization_code";

    private String clientId;

    private String redirectUri;

    private String code;

    public KakaoTokenRequest(String code) {
        this.code = code;
    }
}
