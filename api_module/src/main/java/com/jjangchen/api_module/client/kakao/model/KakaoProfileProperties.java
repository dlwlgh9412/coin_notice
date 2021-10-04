package com.jjangchen.api_module.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoProfileProperties {
    private String nickname;

    @SerializedName("profile_image")
    private String profileImage;

    @SerializedName("thumbnail_image")
    private String thumbnailImage;
}
