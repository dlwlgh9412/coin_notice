package com.jjangchen.crawler.client;

import com.jjangchen.crawler.client.quote.binance.BinanceTickerResponse;
import com.jjangchen.crawler.client.quote.binance.RequestBinanceTicker;
import com.jjangchen.crawler.properties.UrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class BinanceRestApiClient {
    private final BinanceRestApiService apiService;

    public BinanceRestApiClient(UrlProperties urlProperties) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlProperties.getBinanceEndPoint())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiService = retrofit.create(BinanceRestApiService.class);
    }

    public Call<List<List<Object>>> getCandleStick(Map<String, Object> data) {
        return apiService.getCandlesStick(data);
    }

    public Call<BinanceTickerResponse> getTicker(RequestBinanceTicker request) {
        return apiService.getTicker(request.getSymbol());
    }
}
