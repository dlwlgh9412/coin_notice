package com.jjangchen.crawler.client.quote.Bithumb;

public enum BithumbCurrency {
    BTC("BTC"),
    ETH("ETH"),
    KRW("KRW");

    String currency;

    BithumbCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
