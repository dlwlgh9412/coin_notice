package com.jjangchen.api_module.service;

import com.jjangchen.api_module.client.kakao.KaKaoApiClient;
import com.jjangchen.api_module.client.kakao.model.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoService {
    private final KaKaoApiClient kaKaoApiClient;

    public String getToken(String code) throws IOException {
        Response<KakaoTokenResponse> response = kaKaoApiClient.getToken(code).execute();
        if(response.isSuccessful()) {
            return response.body().getAccessToken();
        } else {
            return null;
        }
    }
}
