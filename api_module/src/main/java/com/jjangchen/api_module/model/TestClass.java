package com.jjangchen.api_module.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jjangchen.api_module.client.kakao.KakaoConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TestClass {
    @JsonProperty("grant_type")
    private String grantType = "authorization_code";

    @JsonProperty("client_id")
    private String clientId = KakaoConstants.APP_KEY;

    @JsonProperty("redirect_uri")
    private String redirectUri = "http://localhost:5001/auth/kakao/login";

    @JsonProperty("code")
    private String code;
}
