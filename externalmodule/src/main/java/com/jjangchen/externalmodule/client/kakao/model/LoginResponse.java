package com.jjangchen.externalmodule.client.kakao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private KakaoTokenResponse tokenResponse;
    private KakaoTokenInfo kakaoTokenInfo;
    private KakaoUserInfo kakaoUserInfo;
}
