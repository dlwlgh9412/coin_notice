package com.jjangchen.crawler.client.quote.upbit.candleStick;

public enum UpbitCandleStickInterval {
    OneMinute(1),
    ThreeMinute(3),
    FiveMinute(5),
    TenMinute(10),
    FifteenMinute(15),
    ThirtyMinute(30),
    SixtyMinute(60),
    TwoHunFortyMinute(24);

    Integer interval;
    UpbitCandleStickInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getInterval() {
        return interval;
    }
}
