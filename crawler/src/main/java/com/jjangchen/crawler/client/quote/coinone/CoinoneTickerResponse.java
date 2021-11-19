package com.jjangchen.crawler.client.quote.coinone;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoinoneTickerResponse {
    @SerializedName("result")
    private String result; // success, fail..

    @SerializedName("errorCode")
    private String erroCode;

    @SerializedName("timestamp")
    private Integer timestamp;

    @SerializedName("currency")
    private String currency;

    @SerializedName("high")
    private Double highPrice;

    @SerializedName("low")
    private Double lowPrice;

    @SerializedName("last")
    private Double lastPrice;

    @SerializedName("volume")
    private Double volume;
}
