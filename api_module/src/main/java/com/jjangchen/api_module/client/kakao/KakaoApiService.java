package com.jjangchen.api_module.client.kakao;

import com.jjangchen.api_module.client.kakao.model.KakaoProfileResponse;
import com.jjangchen.api_module.client.kakao.model.KakaoTokenResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface KakaoApiService {
    @FormUrlEncoded
    @POST("/oauth/token")
    Call<KakaoTokenResponse> getToken(@Field("grant_type")String grantType, @Field("client_id")String clientId, @Field("redirect_uri")String redirectUrl,
                                      @Field("code")String code);

    @POST("/v2/user/me")
    Call<KakaoProfileResponse> getProfile(@Header("Authorization") String auth);
}

