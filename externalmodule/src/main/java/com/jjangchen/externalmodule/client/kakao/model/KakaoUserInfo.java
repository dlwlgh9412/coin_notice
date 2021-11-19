package com.jjangchen.externalmodule.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfo {
    @SerializedName("id")
    private Long id;

    @SerializedName("has_signed_up")
    private Boolean hasSignedUp;

    @SerializedName("connected_at")
    private String connectedAt;

    @SerializedName("synched_at")
    private String syncedAt;

    @SerializedName("kakao_account")
    private KakaoAccount kakaoAccount;
}
