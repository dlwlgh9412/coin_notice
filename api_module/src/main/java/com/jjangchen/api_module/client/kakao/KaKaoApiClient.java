package com.jjangchen.api_module.client.kakao;

import com.jjangchen.api_module.client.kakao.model.KakaoTokenRequest;
import com.jjangchen.api_module.client.kakao.model.KakaoTokenResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Slf4j
@Component
public class KaKaoApiClient {
    private final KakaoApiService kakaoApiService;


    public KaKaoApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KakaoConstants.REST_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        this.kakaoApiService = retrofit.create(KakaoApiService.class);
    }

    public Call<KakaoTokenResponse> getToken(String code) throws IOException {
        return kakaoApiService.getToken("authorization_code", KakaoConstants.APP_KEY, KakaoConstants.LOGIN_REDIRECT_URI, code);
    }
}