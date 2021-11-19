package com.jjangchen.common.model;

public enum Exchange {
    ALL("ALL"),
    UPBIT("UPBIT"),
    BITHUMB("BITHUMB"),
    BINANCE("BINANCE"),
    COINONE("COINONE"),
    COINBASE("COINBASE");

    String exchange;

    Exchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }
}
