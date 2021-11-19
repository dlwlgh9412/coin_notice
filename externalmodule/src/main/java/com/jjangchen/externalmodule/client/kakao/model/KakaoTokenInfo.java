package com.jjangchen.externalmodule.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoTokenInfo {
    @SerializedName("id")
    private Long id;

    @SerializedName("expires_in")
    private Integer expiredIn;

    @SerializedName("app_id")
    private Integer appId;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("refresh_expires_in")
    private Long refreshExpiresIn;

    @SerializedName("scope")
    private String scope;
}