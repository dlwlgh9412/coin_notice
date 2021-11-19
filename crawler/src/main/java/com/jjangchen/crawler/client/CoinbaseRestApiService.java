package com.jjangchen.crawler.client;

import com.jjangchen.crawler.client.quote.coinbase.CoinbaseCurrencyData;
import com.jjangchen.crawler.client.quote.coinbase.CoinbaseCurrentTimeData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

public interface CoinbaseRestApiService {
    @GET("products/{product_id}/candles")
    Call<List<List<Object>>> getCandles(@Path("product_id") String path, @QueryMap Map<String, Object> queryMap);

    @GET("v2/exchange-rates")
    Call<CoinbaseCurrencyData> getCurrency(@Query("currency") String query);

    @GET("v2/time")
    Call<CoinbaseCurrentTimeData> getCurrentTime();
}
