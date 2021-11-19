package com.jjangchen.externalmodule.web;

import com.jjangchen.externalmodule.web.advice.ValidJwtToken;
import com.jjangchen.externalmodule.client.kakao.KakaoConstants;
import com.jjangchen.externalmodule.service.KakaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/kakao")
public class KakaoRestApi {
    private final KakaoService kakaoService;
    private final KakaoConstants kakaoConstants;

    @ApiOperation(value = "카카오 로그인 성공시 accessToken, refreshToken 반환")
    @ApiResponse(code = 200, message = "\"AccessToken:\":\"{accessToekn}\"\n\"refreshToken\":\"{refreshToken}\"")
    @GetMapping("/login")
    public ResponseEntity<?> login(@ApiIgnore @RequestParam(value = "code", required = false) String code) throws IOException {
        if(code == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Location", kakaoConstants.KAKAO_LOGIN_PAGE);
            return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(kakaoService.issuedJwt(code), HttpStatus.OK);
    }

    @PostMapping("/revoke")
    public ResponseEntity<?> revokePrivacyPolicy() {
        return null;
    }

    @PostMapping("/additional/info")
    @ValidJwtToken
    public ResponseEntity<?> additionalInfo(@RequestHeader("AccessToken") String accessToken) {
        kakaoService.additionalItemAgreement(accessToken);
        return null;
    }

    @ApiOperation(value = "헤더에 토큰값을 담아 로그아웃 요청", notes = "AccessToken : {ACCESS_TOKEN}")
    @ApiResponse(code = 200, message = "반환 값 = id : {로그아웃한 아이디}")
    @PostMapping("/logout")
    @ValidJwtToken
    public ResponseEntity<?> logout(@RequestHeader("AccessToken") String token) throws IOException {
        return new ResponseEntity<>(kakaoService.logout(token), HttpStatus.OK);
    }
}
