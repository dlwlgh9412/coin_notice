package com.jjangchen.crawler.client;

import com.jjangchen.crawler.client.notice.coinone.CoinoneNoticeResponse;
import com.jjangchen.crawler.client.quote.coinone.CoinoneTickerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface CoinoneRestApiService {
    @Headers({
            "origin: https://coinone.co.kr",
            "sec-fetch-mode: cors",
            "sec-fetch-site: same-site",
            "referer: https://coinone.co.kr/"
    })
    @GET
    Call<CoinoneNoticeResponse> getNotices(@Url String url,
                                           @Query("ordering") String ordering,
                                           @Query("page") int page,
                                           @Query("page_size") int page_size);

    @GET("/ticker/")
    Call<CoinoneTickerResponse> getQuote(@Query("currency") String currency);
}
