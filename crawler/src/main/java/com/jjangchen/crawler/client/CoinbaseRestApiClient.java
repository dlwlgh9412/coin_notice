package com.jjangchen.crawler.client;

import com.jjangchen.crawler.client.quote.coinbase.CoinbaseCurrencyData;
import com.jjangchen.crawler.client.quote.coinbase.CoinbaseCurrentTimeData;
import com.jjangchen.crawler.client.quote.coinbase.candleStick.RequestCoinbaseCandleStick;
import com.jjangchen.crawler.properties.UrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

@Component
@Slf4j
public class CoinbaseRestApiClient {
    private final UrlProperties urlProperties;
    private final CoinbaseRestApiService apiService;

    public CoinbaseRestApiClient(UrlProperties urlProperties) {
        this.urlProperties = urlProperties;
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(urlProperties.getCoinBaseEndPoint())
                .build();
        apiService = retrofit.create(CoinbaseRestApiService.class);
    }

    public Call<CoinbaseCurrencyData> getCurrency(String query) {
        return apiService.getCurrency(query);
    }

    public Call<List<List<Object>>> getCandles(RequestCoinbaseCandleStick requestCoinbaseCandleStick) {
        return apiService.getCandles(requestCoinbaseCandleStick.toPath(), requestCoinbaseCandleStick.toQueryMap());
    }

    public Call<CoinbaseCurrentTimeData> getCurrentTime() {
        return apiService.getCurrentTime();
    }
}
