package com.jjangchen.common.model;

public enum Social {
    KAKAO("KAKAO"),
    NAVER("NAVER");
    public String social;

    Social(String social) {
        this.social = social;
    }

    public String getSocial() {
        return this.social;
    }
}
