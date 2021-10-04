package com.jjangchen.api_module.web;

import com.jjangchen.api_module.client.kakao.KaKaoApiClient;
import com.jjangchen.api_module.client.kakao.model.KakaoTokenResponse;
import com.jjangchen.api_module.service.KakaoService;
import com.jjangchen.api_module.web.support.ApiRestSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.io.IOException;
import java.net.http.HttpResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/kakao")
public class KakaoRestApi extends ApiRestSupport {

    private final KakaoService kakaoService;
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam("code") String code) throws IOException {
        return response(kakaoService.getToken(code));
    }
}
