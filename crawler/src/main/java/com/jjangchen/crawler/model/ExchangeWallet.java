package com.jjangchen.crawler.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeWallet {
    private String address;

    private String exchange;

    private String blockChain;

    private String crypto;
}
