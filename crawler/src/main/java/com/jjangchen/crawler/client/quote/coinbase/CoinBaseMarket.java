package com.jjangchen.crawler.client.quote.coinbase;

public enum CoinBaseMarket {
    BTC("BTC"),
    ETH("ETH");

    String currency;

    CoinBaseMarket(String currency) {
        this.currency = currency;
    }

    public String getMarket() {
        return currency;
    }
}
