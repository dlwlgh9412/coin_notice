package com.jjangchen.externalmodule.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoProfile {
    @SerializedName("nickname")
    private String nickname;

    @SerializedName("thumbnail_image_url")
    private String thumbnailImageUrl;

    @SerializedName("profile_image_url")
    private String profileImageUrl;

    @SerializedName("is_default_image")
    private Boolean isDefaultImage;
}
