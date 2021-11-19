package com.jjangchen.externalmodule.client.kakao.model;

import com.jjangchen.externalmodule.client.kakao.KakaoConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoTokenRequest {
    private String grantType = "authorization_code";

    private String clientId = KakaoConstants.APP_KEY;

    private String redirectUri;

    private String code;

    public KakaoTokenRequest(String code) {
        this.code = code;
    }
}
