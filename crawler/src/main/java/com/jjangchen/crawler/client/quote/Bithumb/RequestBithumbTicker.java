package com.jjangchen.crawler.client.quote.Bithumb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RequestBithumbTicker {
    private String orderCurrency;
    private String paymentCurrency;
}
