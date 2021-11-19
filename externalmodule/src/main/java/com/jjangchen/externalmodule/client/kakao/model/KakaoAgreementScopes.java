package com.jjangchen.externalmodule.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoAgreementScopes {
    @SerializedName("id")
    private String id; // 동의항목 ID

    @SerializedName("display_name")
    private String displayName; // 사용자 동의화면에 출력되는 동의 항목의 이름 또는 설명

    @SerializedName("type")
    private String type; /* 동의항목 타입
                            PRIVACY: 개인정보 보호 동의항목
                            SERVICE: 접근권한 관리 동의항목
                            */

    @SerializedName("using")
    private Boolean using; // 해당 앱에서 동의항목 사용여부

    @SerializedName("agreed")
    private Boolean agreed; // 사용자 동의 여부

    @SerializedName("revocable")
    private Boolean revocable; // 동의항목의 동츼철회 가능 여부
}
