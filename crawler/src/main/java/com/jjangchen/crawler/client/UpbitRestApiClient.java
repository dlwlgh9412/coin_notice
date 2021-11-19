package com.jjangchen.crawler.client;

import com.jjangchen.crawler.client.notice.upbit.UpbitNoticeData;
import com.jjangchen.crawler.client.notice.upbit.UpbitNoticeResponse;
import com.jjangchen.crawler.client.quote.upbit.RequestUpbitTicker;
import com.jjangchen.crawler.client.quote.upbit.UpbitTickerResponse;
import com.jjangchen.crawler.client.quote.upbit.candleStick.UpbitCandleStickData;
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
public class UpbitRestApiClient {
    private final UpbitRestApiService upbitRestApiService;
    private final UrlProperties urlProperties;
    public UpbitRestApiClient(UrlProperties urlProperties) {
        this.urlProperties = urlProperties;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlProperties.getUpbitEndPoint())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        this.upbitRestApiService = retrofit.create(UpbitRestApiService.class);
    }

    public Call<List<UpbitCandleStickData>> getCandleStick(Integer path, Map<String, Object> query) {
        return upbitRestApiService.getCandleStick(path, query);
    }

    public Call<UpbitNoticeResponse<UpbitNoticeData>> getNotices(String threadName) {
        return upbitRestApiService.getNotices(urlProperties.getUpbitEventUrl(), threadName);
    }

    public Call<List<UpbitTickerResponse>> getTicker(RequestUpbitTicker request) {
        return upbitRestApiService.getTicker(request.getUpbitMarket().getMarket());
    }
}
