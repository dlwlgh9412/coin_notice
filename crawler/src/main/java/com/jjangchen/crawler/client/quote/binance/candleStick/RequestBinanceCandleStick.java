package com.jjangchen.crawler.client.quote.binance.candleStick;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestBinanceCandleStick {
    @NotBlank
    private String symbol;

    @NotBlank
    private BinanceCandleStickInterval interval;

    @Min(1)
    @Max(1000)
    private Integer limit;

    public Map<String, Object> toQueryMap() {
        return ImmutableMap.of(
                "symbol", symbol,
                "interval", interval.getInterval(),
                "limit", limit
        );
    }
}
