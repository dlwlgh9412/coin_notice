package com.jjangchen.externalmodule.client.kakao;

import com.jjangchen.externalmodule.client.kakao.model.KakaoTokenInfo;
import com.jjangchen.externalmodule.client.kakao.model.KakaoTokenResponse;
import com.jjangchen.externalmodule.client.kakao.model.KakaoLogout;
import com.jjangchen.externalmodule.client.kakao.model.KakaoUserInfo;
import retrofit2.Call;
import retrofit2.http.*;

public interface KakaoApiService {
    @FormUrlEncoded
    @POST
    Call<KakaoTokenResponse> getToken(@Url String url,
                                      @Field("grant_type") String grantType,
                                      @Field("client_id") String clientId,
                                      @Field("redirect_uri") String redirectUrl,
                                      @Field("code") String code
            /*@Field("client_secret")String clientSecret*/);

    @POST("/v1/user/logout")
    Call<KakaoLogout> logout(@Header("Authorization") String token);

    @GET("/v1/user/access_token_info")
    Call<KakaoTokenInfo> getAccessTokenInfo(@Header("Authorization") String token);

    @GET("/v2/user/me")
    Call<KakaoUserInfo> getUserInfo(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST
    Call<KakaoTokenResponse> reissuedToken(@Url String url, @Field("grant_type") String grantType,
                                           @Field("client_id") String clientId,
                                           @Field("refresh_token") String refreshToken
            /*@Field("client_secret")String clientSecret*/);
}

