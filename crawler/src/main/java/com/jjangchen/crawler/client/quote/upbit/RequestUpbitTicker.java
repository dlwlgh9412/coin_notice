package com.jjangchen.crawler.client.quote.upbit;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUpbitTicker {
    private UpbitMarket upbitMarket;
}
