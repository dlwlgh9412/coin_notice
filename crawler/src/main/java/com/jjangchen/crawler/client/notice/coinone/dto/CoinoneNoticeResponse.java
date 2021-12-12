package com.jjangchen.crawler.client.notice.coinone.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoinoneNoticeResponse {
    @JsonProperty("count")
    private Long count;

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private String previous;

    @JsonProperty("results")
    private List<CoinoneNoticeData> coinoneNoticeData;
}
