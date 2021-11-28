package com.jjangchen.externalmodule.client.kakao;

import com.google.gson.GsonBuilder;
import com.jjangchen.externalmodule.client.kakao.model.KakaoTokenInfo;
import com.jjangchen.externalmodule.client.kakao.model.KakaoTokenResponse;
import com.jjangchen.externalmodule.client.kakao.model.KakaoLogout;
import com.jjangchen.externalmodule.client.kakao.model.KakaoUserInfo;
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
    private final KakaoConstants kakaoConstants;

    public KaKaoApiClient(KakaoConstants kakaoConstants) {
        this.kakaoConstants = kakaoConstants;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KakaoConstants.KAKAO_API_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        this.kakaoApiService = retrofit.create(KakaoApiService.class);
    }

    public Call<KakaoUserInfo> getUserInfo(String token) {
        return kakaoApiService.getUserInfo("Bearer " + token);
    }

    public Call<KakaoTokenInfo> getAccessTokenInfo(String token) {
        return kakaoApiService.getAccessTokenInfo("Bearer " + token);
    }

    public Call<KakaoTokenResponse> getToken(String code) throws IOException {
        return kakaoApiService.getToken(KakaoConstants.KAKAO_AUTH_URL + "/oauth/token", "authorization_code", kakaoConstants.APP_KEY, kakaoConstants.KAKAO_TOKEN_REDIRECT_URI, code);
    }

    public Call<KakaoTokenResponse> reissuedAccessToken(String refreshToken) {
        return kakaoApiService.reissuedToken(KakaoConstants.KAKAO_AUTH_URL + "/oauth/token", "refresh_token", kakaoConstants.APP_KEY, refreshToken);
    }
    public Call<KakaoLogout> logout(String token) {
        return kakaoApiService.logout("Bearer " + token);
    }
}