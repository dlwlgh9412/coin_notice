package com.jjangchen.common.model;

public enum Exchange {
    ALL("ALL"),
    UPBIT("UPBIT"),
    BITHUMB("BITHUMB"),
    GDAC("GDAC"),
    OKEX("OKEX"),
    BITSONIC("BITSONIC"),
    HUOBI("HOUBI"),
    COINONE("COINONE"),
    BINANCE("BINANCE"),
    BITFINEX("BITFINEX");

    String exchange;

    Exchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchange() {
        return exchange;
    }
}
