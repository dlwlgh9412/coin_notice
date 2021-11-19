package com.jjangchen.externalmodule.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoAgreement {
    @SerializedName("id")
    private Long id; // 아이디

    @SerializedName("scopes")
    private KakaoAgreementScopes scopes;

}
