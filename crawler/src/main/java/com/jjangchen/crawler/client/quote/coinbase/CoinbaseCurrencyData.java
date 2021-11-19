package com.jjangchen.crawler.client.quote.coinbase;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoinbaseCurrencyData {
    @SerializedName("data")
    private CoinbaseCurrency coinbaseCurrency;
}
