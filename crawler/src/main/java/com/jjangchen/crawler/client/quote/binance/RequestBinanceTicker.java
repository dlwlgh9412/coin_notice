package com.jjangchen.crawler.client.quote.binance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class RequestBinanceTicker {
    private String symbol;
}
