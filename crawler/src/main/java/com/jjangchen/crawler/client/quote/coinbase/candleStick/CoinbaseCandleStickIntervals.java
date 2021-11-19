package com.jjangchen.crawler.client.quote.coinbase.candleStick;

public enum CoinbaseCandleStickIntervals {
    OneMinute(60),
    FiveMinute(300),
    FifteenMinute(900),
    OneHour(3600),
    SixHour(21600),
    OneDay(86400);

    Integer interval;

    CoinbaseCandleStickIntervals(Integer interval) {
        this.interval = interval;
    }

    public Integer getInterval() {
        return interval;
    }
}
