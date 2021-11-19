package com.jjangchen.crawler.client;

import com.jjangchen.crawler.client.quote.Bithumb.BithumbTickerResponse;
import com.jjangchen.crawler.client.quote.Bithumb.RequestBithumbTicker;
import com.jjangchen.crawler.properties.UrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
@Slf4j
public class BithumbRestApiClient {
    private final BithumbRestApiService restApiService;
    private final UrlProperties urlProperties;

    public BithumbRestApiClient(UrlProperties urlProperties) {
        this.urlProperties = urlProperties;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlProperties.getBithumbEndPoint())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        restApiService = retrofit.create(BithumbRestApiService.class);
    }

    public Call<BithumbTickerResponse> getTicker(RequestBithumbTicker data) {
        return restApiService.getTicker(data.getOrderCurrency(), data.getPaymentCurrency());
    }

}
