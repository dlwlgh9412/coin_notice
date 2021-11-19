package com.jjangchen.externalmodule.web.model.kakao;

public enum RevokeList {
    EMAIL("account_email"),
    NICKNAME("nickname"),
    PROFILE_IMAGE_URL("profile_image_url");

    public String target;

    RevokeList(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
}
