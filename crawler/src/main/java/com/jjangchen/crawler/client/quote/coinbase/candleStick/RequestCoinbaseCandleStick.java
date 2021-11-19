package com.jjangchen.crawler.client.quote.coinbase.candleStick;

import com.google.common.collect.ImmutableMap;
import com.jjangchen.crawler.client.quote.coinbase.CoinBaseMarket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCoinbaseCandleStick {
    private CoinBaseMarket market;
    private CoinbaseCandleStickIntervals interval;

    public String toPath() {
        return market.getMarket();
    }

    public Map<String, Object> toQueryMap() {
        return ImmutableMap.of(
                "granularity", interval.getInterval()
        );
    }
}
