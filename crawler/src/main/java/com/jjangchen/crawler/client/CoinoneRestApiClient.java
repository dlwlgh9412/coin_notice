package com.jjangchen.crawler.client;

import com.jjangchen.crawler.client.notice.coinone.CoinoneNoticeResponse;
import com.jjangchen.crawler.client.notice.coinone.RequestCoinoneNotice;
import com.jjangchen.crawler.client.quote.coinone.CoinoneTickerResponse;
import com.jjangchen.crawler.client.quote.coinone.RequestCoinoneTicker;
import com.jjangchen.crawler.properties.UrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Slf4j
@Component
public class CoinoneRestApiClient {
    private final CoinoneRestApiService coinoneRestApiService;
    private final UrlProperties urlProperties;

    public CoinoneRestApiClient(UrlProperties urlProperties) {
        this.urlProperties = urlProperties;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlProperties.getCoinoneEndPoint())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.coinoneRestApiService = retrofit.create(CoinoneRestApiService.class);
    }

    public Call<CoinoneNoticeResponse> getNotices(RequestCoinoneNotice requestCoinoneNotice) {
        return coinoneRestApiService.getNotices(
                requestCoinoneNotice.getUrl(),
                requestCoinoneNotice.getOrdering(),
                requestCoinoneNotice.getPage(),
                requestCoinoneNotice.getPage_size());

    }

    public Call<CoinoneTickerResponse> getQuotes(RequestCoinoneTicker coinoneTicker) {
        return coinoneRestApiService.getQuote(coinoneTicker.getCurrency());
    }
}
