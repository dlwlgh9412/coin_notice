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

    @Value("${upbit.endPoint}")
    private String upbitEndPoint;

    @Value("${binance.endPoint}")
    private String binanceEndPoint;

    @Value("${coinbase.endPoint}")
    private String coinBaseEndPoint;

    @Value("${bithumb.endPoint}")
    private String bithumbEndPoint;

    @Value("${coinone.endPoint}")
    private String coinoneEndPoint;
}
