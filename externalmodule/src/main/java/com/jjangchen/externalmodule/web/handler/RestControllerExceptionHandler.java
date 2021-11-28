package com.jjangchen.externalmodule.web.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.jjangchen.externalmodule.client.kakao.KakaoConstants;
import com.jjangchen.externalmodule.web.exception.BearerException;
import com.jjangchen.externalmodule.web.exception.EmailNullException;
import com.jjangchen.externalmodule.web.exception.AccountNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class RestControllerExceptionHandler {
    private final KakaoConstants kakaoConstants;

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> expiredToken() {
        return new ResponseEntity<>(ResponseErrorBody.builder()
                .error("expiredToken")
                .errorDescription("expiredToken")
                .errorCode("2")
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BearerException.class)
    public ResponseEntity<?> notJwtToken() {
        return new ResponseEntity<>(ResponseErrorBody.builder()
                .error("Not Start with Bearer")
                .errorDescription("Put Bearer in front of the token")
                .errorCode("-1")
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> notValidToken() {
        return new ResponseEntity<>(ResponseErrorBody.builder()
                .error("Not Valid Token")
                .errorDescription("Try Login Again")
                .errorCode("1")
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<?> notAllowedCharacter() {
        return new ResponseEntity<>(ResponseErrorBody.builder()
                .error("Not Allowed Character")
                .errorDescription("Check your Character")
                .errorCode("401")
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> notExistAccount() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "" /* 계정등록 화면 */);
        return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
    }

    @ExceptionHandler(EmailNullException.class)
    public ResponseEntity<?> emailNull() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", KakaoConstants.KAKAO_AUTH_URL + "/oauth/authorize?client_id=" +
                kakaoConstants.APP_KEY + "&redirect_uri=" + kakaoConstants.KAKAO_TOKEN_REDIRECT_URI +
                "&response_type=code" + "&scope=account_email");
        return new ResponseEntity<>(httpHeaders, HttpStatus.FOUND);
    }
}