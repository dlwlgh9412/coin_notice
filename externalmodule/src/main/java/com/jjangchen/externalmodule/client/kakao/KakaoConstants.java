package com.jjangchen.externalmodule.client.kakao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@EnableConfigurationProperties
@PropertySource("classpath:properties/url-${spring.profiles.active}.properties")
public class KakaoConstants {
    public static String KAKAO_AUTH_URL = "https://kauth.kakao.com";
    public static String KAKAO_API_URL = "https://kapi.kakao.com";
    @Value("${kakao.appkey}")
    public String APP_KEY;

    @Value("${kakao.login_page}")
    public String KAKAO_LOGIN_PAGE;
    @Value("${kakao.token_redirect_uri}")
    public String KAKAO_TOKEN_REDIRECT_URI;

    @Value("${kakao.scope_addtion_redirect_uri}")
    public String KAKAO_SCOPE_ADDTION_REDIRECT_URI;
}
