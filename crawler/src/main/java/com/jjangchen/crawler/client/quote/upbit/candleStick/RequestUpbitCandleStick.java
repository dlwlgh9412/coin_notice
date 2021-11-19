package com.jjangchen.crawler.client.quote.upbit.candleStick;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUpbitCandleStick {
    @NotNull
    private Integer interval; // minute(unit)
    @NotNull
    private String market;
    private String to; // UTC yyyy-MM-dd'T'HH:mm:ss'Z'
    private Integer count;

    public Integer toPath() {
        return interval;
    }

    public Map<String, Object> toQueryMap() {
        return ImmutableMap.of(
                "market", market,
                "count", count
        );
    }
}
