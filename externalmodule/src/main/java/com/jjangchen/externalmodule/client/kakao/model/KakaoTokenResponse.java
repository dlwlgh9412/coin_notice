package com.jjangchen.externalmodule.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import com.jjangchen.common.util.CommonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoTokenResponse {
    private final Long tokenIssuanceTime = CommonUtil.getCurrentTimeToTimestamp();

    @SerializedName("token_type")
    private String tokenType;

    private Long kakaoAccessTokenIssuanceTime = tokenIssuanceTime;

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("expires_in")
    private Integer expiresIn;

    private Long kakaoRefreshTokenIssuanceTime = tokenIssuanceTime;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("refresh_token_expires_in")
    private Integer refreshTokenExpiresIn;

    @SerializedName("scope")
    private String scope;
}
