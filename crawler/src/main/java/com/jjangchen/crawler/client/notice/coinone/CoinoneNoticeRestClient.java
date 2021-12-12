package com.jjangchen.crawler.client.notice.coinone;

import com.jjangchen.crawler.client.model.RequestURI;
import com.jjangchen.crawler.client.notice.coinone.dto.CoinoneNoticeResponse;
import com.jjangchen.crawler.properties.UrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class CoinoneNoticeRestClient {
    private final WebClient webClient;

    public CoinoneNoticeRestClient(UrlProperties urlProperties) {
        this.webClient = WebClient.builder()
                .baseUrl(urlProperties.getCoinoneEventUrl())
                .build();
    }

    public CoinoneNoticeResponse getNotices(RequestURI requestURI) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(requestURI.getPath())
                        .queryParams(requestURI.getParams())
                        .build())
                .headers(headers -> {
                    headers.add("origin", "https://coinone.co.kr");
                    headers.add("sec-fetch-mode", "cors");
                    headers.add("sec-fetch-site", "same-site");
                    headers.add("referer", "https://coinone.co.kr");
                })
                .retrieve()
                .bodyToMono(CoinoneNoticeResponse.class).block();
    }
}
