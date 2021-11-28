package com.jjangchen.externalmodule.web;

import com.jjangchen.externalmodule.service.AccountService;
import com.jjangchen.externalmodule.service.JWTService;
import com.jjangchen.externalmodule.web.support.ApiRestSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class AccountRestApi extends ApiRestSupport {
    private final JWTService jwtService;
    //private final AccountService accountService;

    @PostMapping("/auth/reissued")
    public ResponseEntity<?> reissued(@RequestHeader("RefreshToken") String refreshToken) {
        return new ResponseEntity<>(jwtService.reissuedAccessToken(refreshToken), HttpStatus.OK);
    }

    @PostMapping("/kakao/additional/info")
    public ResponseEntity<?> additionalInfo(@RequestParam("code") String code) {
        return null;
    }

    @PostMapping("/revoke")
    public ResponseEntity<?> revokePrivacyPolicy() {
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@RequestHeader("AccessToken") String accessToken) {
        return null;
    }

    @ApiOperation(value = "로그인 성공시 accessToken, refreshToken 반환")
    @ApiResponse(code = 200, message = "\"AccessToken:\":\"{accessToekn}\"\n\"refreshToken\":\"{refreshToken}\"")
    @PostMapping("/issueToken")
    public ResponseEntity<?> kakaoLogin() {
        return null;
    }
}
