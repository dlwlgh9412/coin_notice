package com.jjangchen.crawler.client.upbit;

import com.jjangchen.crawler.client.upbit.model.UpbitNoticeData;
import com.jjangchen.crawler.client.upbit.model.UpbitNoticeResponse;
import com.jjangchen.crawler.properties.UrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
@Slf4j
public class UpbitRestApiClient {
    private final UpbitRestApiService upbitRestApiService;

    public UpbitRestApiClient(UrlProperties urlProperties) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlProperties.getUpbitEventUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        this.upbitRestApiService = retrofit.create(UpbitRestApiService.class);
    }

    public Call<UpbitNoticeResponse<UpbitNoticeData>> getNotices(String threadName) {
        return upbitRestApiService.getNotices(threadName);
    }
}
