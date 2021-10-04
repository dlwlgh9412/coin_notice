package com.jjangchen.api_module.client.kakao.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class KakaoProfile {

    private String nickname;

    @SerializedName("profile_image_url")
    private String profileImage;

    @SerializedName("thumbnail_image_url")
    private String thumbnailImage;
}
