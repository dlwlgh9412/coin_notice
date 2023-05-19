package com.jjangchen.crawler.client;

import com.jjangchen.crawler.client.notice.upbit.dto.UpbitNoticeData;
import com.jjangchen.crawler.client.notice.upbit.dto.UpbitNoticeResponse;
import com.jjangchen.crawler.client.quote.upbit.UpbitTickerResponse;
import com.jjangchen.crawler.client.quote.upbit.candleStick.UpbitCandleStickData;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

@Component
public interface UpbitRestApiService {
    @GET("/candles/minutes/{unit}")
    Call<List<UpbitCandleStickData>> getCandleStick(@Path("unit") Integer path, @QueryMap Map<String, Object> query);

    @GET("/ticker")
    Call<List<UpbitTickerResponse>> getTicker(@Query("markets") String markets);

}
