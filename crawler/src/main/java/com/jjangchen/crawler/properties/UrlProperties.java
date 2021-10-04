package com.jjangchen.crawler.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@EnableConfigurationProperties
@PropertySource("classpath:properties/url-${spring.profiles.active}.properties")
public class UrlProperties {
    @Value("${upbit.eventUrl}")
    private String upbitEventUrl;
}
