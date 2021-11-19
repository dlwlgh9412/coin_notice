package com.jjangchen.crawler.client.quote.upbit.candleStick;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpbitCandleStickData {
    @SerializedName("market")
    private String market;

    @SerializedName("candle_date_time_utc")
    private String candleDateTimeUtc;

    @SerializedName("candle_date_time_kst")
    private String candleDateTimeKst;

    @SerializedName("opening_price")
    private Double openingPrice;

    @SerializedName("high_price")
    private Double highPrice;

    @SerializedName("low_price")
    private Double lowPrice;

    @SerializedName("trade_price")
    private Double tradePrice;

    @SerializedName("timestamp")
    private Long timestamp;

}
