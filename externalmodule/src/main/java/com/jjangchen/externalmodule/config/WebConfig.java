package com.jjangchen.externalmodule.config;

import com.jjangchen.externalmodule.web.interceptor.CustomInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor()).excludePathPatterns("/oauth/kakao/login", "/oauth/kakao/issuetoken");
    }
}
