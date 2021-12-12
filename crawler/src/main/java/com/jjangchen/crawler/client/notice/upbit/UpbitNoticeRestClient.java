package com.jjangchen.crawler.client.notice.upbit;

import com.jjangchen.crawler.client.model.RequestURI;
import com.jjangchen.crawler.client.notice.upbit.dto.UpbitNoticeResponse;
import com.jjangchen.crawler.properties.UrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class UpbitNoticeRestClient {
    private final WebClient webClient;

    public UpbitNoticeRestClient(UrlProperties urlProperties) {
        this.webClient = WebClient.builder()
                .baseUrl(urlProperties.getUpbitEventUrl())
                .build();
    }

    public UpbitNoticeResponse getNotice(RequestURI requestURI) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(requestURI.getPath())
                        .queryParams(requestURI.getParams())
                        .build())
                .retrieve()
                .bodyToMono(UpbitNoticeResponse.class).block();
    }

}
