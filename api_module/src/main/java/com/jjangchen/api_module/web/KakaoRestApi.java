package com.jjangchen.api_module.web;

import com.jjangchen.api_module.service.KakaoService;
import com.jjangchen.api_module.web.support.ApiRestSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
