package com.jjangchen.crawler.client;

import com.jjangchen.crawler.client.quote.binance.BinanceTickerResponse;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

@Component
public interface BinanceRestApiService {
    @GET("api/v3/klines")
    Call<List<List<Object>>> getCandlesStick(@QueryMap Map<String, Object> data);

    @GET("api/v3/ticker/24hr")
    Call<BinanceTickerResponse> getTicker(@Query("symbol") String symbol);
}
