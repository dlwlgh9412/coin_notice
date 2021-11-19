package com.jjangchen.crawler.client.quote.upbit;


public enum UpbitMarket {
    KRWBTC("KRW-BTC"),
    KRWETH("KRW-ETH");

    String market;
    UpbitMarket(String market) {
        this.market = market;
    }

    public String getMarket() {
        return market;
    }
}
