package com.jjangchen.externalmodule.config;

import com.jjangchen.externalmodule.web.interceptor.CustomInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor()).excludePathPatterns("/auth/kakao/**").excludePathPatterns("/auth/issuetoken");
    }

    @Bean
    public CustomInterceptor customInterceptor() {
        return new CustomInterceptor();
    }
}
