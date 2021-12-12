package com.jjangchen.crawler.client.notice.upbit.dto;

import com.jjangchen.crawler.client.model.RequestURI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpbitNotice implements RequestURI {
    private String path;
    private String threadName;

    @Override
    public MultiValueMap<String, String> getParams() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("threadname", threadName);
        return params;
    }
}
