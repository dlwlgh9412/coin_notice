package com.jjangchen.crawler.client;

import com.jjangchen.crawler.client.notice.upbit.UpbitNoticeData;
import com.jjangchen.crawler.client.notice.upbit.UpbitNoticeResponse;
import com.jjangchen.crawler.client.quote.upbit.UpbitTickerResponse;
import com.jjangchen.crawler.client.quote.upbit.candleStick.UpbitCandleStickData;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface UpbitRestApiService {
    @GET("v1/candles/minutes/{unit}")
    Call<List<UpbitCandleStickData>> getCandleStick(@Path("unit") Integer path, @QueryMap Map<String, Object> query);

    @GET
    Call<UpbitNoticeResponse<UpbitNoticeData>> getNotices(@Url String url, @Query("thread_name") String threadName);

    @GET("v1/ticker")
    Call<List<UpbitTickerResponse>> getTicker(@Query("markets") String markets);

}
