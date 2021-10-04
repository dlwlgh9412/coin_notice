package com.jjangchen.api_module.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoProfileResponse {
    private Long id;
    private KakaoProfileProperties kakaoProfileProperties;

    @SerializedName("kakao_account")
    private KakaoAccountInfo kakaoProfileAccount;
}
