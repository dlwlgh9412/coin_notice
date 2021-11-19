package com.jjangchen.crawler.client.quote.binance.candleStick;

public enum BinanceCandleStickInterval {
    OneMinute("1m"),
    ThreeMinute("3m"),
    FiveMinute("5m"),
    FifteenMinute("15m"),
    ThirtyMinute("30m"),
    OneHour("1h"),
    TwoHour("2h"),
    FourHour("4h"),
    SixHour("6h"),
    EightHour("8h"),
    TwelveHour("12h"),
    OneDay("1d"),
    ThreeDay("3d"),
    OneWeek("1w"),
    OneMonth("1M");

    String interval;
    BinanceCandleStickInterval(String interval) {
        this.interval = interval;
    }
    public String getInterval() {
        return interval;
    }
}
