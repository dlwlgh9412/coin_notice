package com.jjangchen.crawler.client.notice.coinone.dto;

import com.jjangchen.crawler.client.model.RequestURI;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class RequestCoinoneNotice implements RequestURI {
    private String path;
    private String ordering;
    private int page;
    private int page_size;

    public RequestCoinoneNotice(String path, String ordering, int page, int page_size) {
        this.path = path;
        this.ordering = ordering;
        this.page = page;
        this.page_size = page_size;
    }

    @Override
    public MultiValueMap<String, String> getParams() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("ordering", ordering);
        params.add("page", String.valueOf(page));
        params.add("page_size", String.valueOf(page_size));
        return params;
    }
}
