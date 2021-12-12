package com.jjangchen.crawler.client.quote.coinone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinoneQuoteRestClientService {
    @GET("/ticker/")
    Call<CoinoneTickerResponse> getQuote(@Query("currency") String currency);
}