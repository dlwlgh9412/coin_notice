package com.jjangchen.crawler.client.notice.coinone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoinoneNoticeFlaggedContent {
    @JsonProperty("status")
    private String status;
}
