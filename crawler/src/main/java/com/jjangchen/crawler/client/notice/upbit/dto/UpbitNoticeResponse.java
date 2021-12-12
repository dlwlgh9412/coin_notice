package com.jjangchen.crawler.client.notice.upbit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpbitNoticeResponse {
    @JsonProperty("success")
    private String success;

    @JsonProperty("data")
    private UpbitNoticeData data;
}
