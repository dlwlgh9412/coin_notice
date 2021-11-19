package com.jjangchen.crawler.client.notice.coinone;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestCoinoneNotice {
    private String url = "http://i1.coinone.co.kr/api/talk/notice";
    private String ordering = "-created_at";
    private int page;
    private int page_size;

    public RequestCoinoneNotice(int page, int page_size) {
        this.page = page;
        this.page_size = page_size;
    }

}
