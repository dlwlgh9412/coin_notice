package com.jjangchen.externalmodule.web;

import com.jjangchen.externalmodule.web.advice.ValidJwtToken;
import com.jjangchen.externalmodule.client.kakao.KakaoConstants;
import com.jjangchen.externalmodule.service.SocialService;
import com.jjangchen.externalmodule.web.support.ApiRestSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class SocialRestApi extends ApiRestSupport {
    private final SocialService socialService;
    private final KakaoConstants kakaoConstants;

    @GetMapping("/kakao/login")
    public void kakaoLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect(kakaoConstants.KAKAO_LOGIN_PAGE);
    }

    @ApiOperation(value = "로그인 성공시 accessToken, refreshToken 반환")
    @ApiResponse(code = 200, message = "\"accessToken:\":\"{accessToekn}\"\n\"refreshToken\":\"{refreshToken}\"")
    @PostMapping("/issuetoken")
    public ResponseEntity<?> issueToken(@RequestParam(value = "code") String code) {
        return response(socialService.requestKakaoToken(code));
    }

    @ApiOperation(value = "헤더에 토큰값을 담아 로그아웃 요청", notes = "AccessToken : {ACCESS_TOKEN}")
    @ApiResponse(code = 200, message = "반환 값 = id : {로그아웃한 아이디}")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("acessToken") String token) throws IOException {
        return new ResponseEntity<>(socialService.logout(token), HttpStatus.OK);
    }
}
