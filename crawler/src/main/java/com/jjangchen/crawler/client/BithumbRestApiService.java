package com.jjangchen.crawler.client;

import com.jjangchen.crawler.client.quote.Bithumb.BithumbTickerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BithumbRestApiService {
    @GET("public/ticker/{order_currency}_{payment_currency}")
    Call<BithumbTickerResponse> getTicker(@Path("order_currency") String orderCurrency, @Path("payment_currency") String paymentCurrency);
}
