package com.jjangchen.externalmodule.web;

import com.jjangchen.externalmodule.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserRestApi {
    private final JwtService jwtService;

    @PostMapping("/auth/reissued")
    public ResponseEntity<?> reissued(@RequestHeader("RefreshToken") String refreshToken) {
        return new ResponseEntity<>(jwtService.reissuedAccessToken(refreshToken), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@RequestHeader("AccessToken") String accessToken) {
        return null;
    }
}
