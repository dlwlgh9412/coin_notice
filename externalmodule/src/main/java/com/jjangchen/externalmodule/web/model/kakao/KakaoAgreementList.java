package com.jjangchen.externalmodule.web.model.kakao;

public enum KakaoAgreementList {
    PROFILE("profile"),
    PROFILE_NICKNAME("profile_nickname"),
    PROFILE_IMAGE("profile_image"),
    NAME("name"),
    ACCOUNT_EMAIL("account_email"),
    GENDER("GENDER"),
    AGE_RANGE("age_range"),
    BIRTHDAY("birthday"),
    BIRTHYEAR("birthyear"),
    PHONE_NUMBER("PHONE_NUMBER"),
    ACCOUNT_CICI("account_cici"), // CI 연계정보
    FRIENDS("friends"), // 카카오 서비스 내 친구목록
    PLUSFRIENDS("plusfriends"), // 카카오톡 채널 추가 상태 및 내역
    SHIPPING_ADDRESS("shipping_address"), // 배송지 정보
    URL("story_permalink"); // 카카오스토리 프로필

    String agreement;

    KakaoAgreementList(String agreement) {
        this.agreement = agreement;
    }

    public String getAgreement(String agreement) {
        return this.agreement;
    }
}
