package com.jjangchen.crawler.client.quote.binance;

public enum BinanceSymbol {
    BTCUSDT("BTCUSDT"),
    ETHUSDT("ETHUSDT");

    String symbol;

    BinanceSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
