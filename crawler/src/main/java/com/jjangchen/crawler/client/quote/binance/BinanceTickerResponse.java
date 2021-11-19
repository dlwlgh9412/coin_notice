package com.jjangchen.crawler.client.quote.binance;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BinanceTickerResponse {
    @SerializedName("symbol")
    private String symbol;

    @SerializedName("priceChange")
    private String priceChange;

    @SerializedName("priceChangePercent")
    private String priceChangePercent;

    @SerializedName("weightedAvgPrice")
    private String weightedAvgPrice;

    @SerializedName("prevClosePrice")
    private String prevClosePrice;

    @SerializedName("lastPrice")
    private String lastPrice;

    @SerializedName("openPrice")
    private String openPrice;

    @SerializedName("highPrice")
    private String highPrice;

    @SerializedName("lowPrice")
    private String lowPrice;

    @SerializedName("openTime")
    private Long openTIme;

    @SerializedName("closeTime")
    private Long closeTime;

}
