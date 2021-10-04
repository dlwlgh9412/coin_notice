package com.jjangchen.crawler.client.upbit;

import com.jjangchen.crawler.client.upbit.model.UpbitNoticeData;
import com.jjangchen.crawler.client.upbit.model.UpbitNoticeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpbitRestApiService {
    @GET("/api/v1/notices")
    Call<UpbitNoticeResponse<UpbitNoticeData>> getNotices(@Query("thread_name") String threadName);
}
