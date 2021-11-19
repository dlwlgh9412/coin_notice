package com.jjangchen.externalmodule.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoAccount {
    @SerializedName("profile_needs_agreement")
    private Boolean profileNeedsAgreement;

    @SerializedName("profile_nickname_needs_agreement")
    private Boolean profileNicknameNeedsAgreement;

    @SerializedName("profile_image_needs_agreement")
    private Boolean profileImageNeedsAgreement;

    @SerializedName("profile")
    private KakaoProfile profile;

    @SerializedName("email_needs_agreement")
    private Boolean emailNeedsAgreement;

    @SerializedName("is_email_valid")
    private Boolean isEmailValid;

    @SerializedName("is_email_verified")
    private Boolean isEmailVerified;

    @SerializedName("email")
    private String email;
}
