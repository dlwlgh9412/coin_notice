package com.jjangchen.externalmodule.service;

import com.jjangchen.common.entity.UserEntity;
import com.jjangchen.common.exception.AccessTokenExpiresException;
import com.jjangchen.common.repository.UserRepository;
import com.jjangchen.common.util.CommonUtil;
import com.jjangchen.externalmodule.client.kakao.KaKaoApiClient;
import com.jjangchen.externalmodule.client.kakao.model.KakaoLogout;
import com.jjangchen.externalmodule.client.kakao.model.KakaoTokenInfo;
import com.jjangchen.externalmodule.client.kakao.model.KakaoTokenResponse;
import com.jjangchen.externalmodule.client.kakao.model.KakaoUserInfo;
import com.jjangchen.externalmodule.web.exception.UserNotFoundException;
import com.jjangchen.externalmodule.web.model.JwtObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Slf4j
@Service
public class KakaoService {
    private final KaKaoApiClient kaKaoApiClient;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtObject issuedJwt(String code) {
        KakaoTokenResponse kakaoTokenResponse = getKakaoToken(code);
        return jwtService.getTokenObj(getUserEntity(getUserInfo(kakaoTokenResponse.getAccessToken()), kakaoTokenResponse));
    }

    // 추가 동의항목
    @Transactional
    public void additionalItemAgreement(String accessToken) throws SignatureException, ExpiredJwtException, UserNotFoundException {
        Claims claims = jwtService.parseToken(accessToken);
        UserEntity userEntity = userRepository.findById(Long.valueOf(claims.get("id").toString())).orElseThrow(UserNotFoundException::new);
        try {
            CommonUtil.checkExpiresToken(userEntity.getKakaoAccessTokenIssuanceTime(), userEntity.getKakaoAccessTokenExpiresIn(), "Kakao AccessToken Expired");
        } catch (AccessTokenExpiresException e) {
            // 카카오 액세스 토큰 만료 시 리프레시 토큰 조회 후 갱신
            log.error(e.getMessage());
            KakaoTokenResponse kakaoTokenResponse = reissuedAccessToken(userEntity.getKakaoRefreshToken());
            userEntity = userRepository.save(userEntity.reissuedKakaoToken(kakaoTokenResponse.getAccessToken(),
                    kakaoTokenResponse.getExpiresIn(),
                    kakaoTokenResponse.getRefreshToken(),
                    kakaoTokenResponse.getRefreshTokenExpiresIn()));
        } finally {
            KakaoUserInfo kakaoUserInfo = getUserInfo(userEntity.getKakaoAccessToken());
            log.info("Eamil Needs Agreement: {}", kakaoUserInfo.getKakaoAccount().getEmailNeedsAgreement());
            log.info("Profile Needs Agreement: {}", kakaoUserInfo.getKakaoAccount().getProfileNeedsAgreement());
            log.info("Profile Nickname needs agreement: {}", kakaoUserInfo.getKakaoAccount().getProfileNicknameNeedsAgreement());
            log.info("is Email Vaild: {}", kakaoUserInfo.getKakaoAccount().getIsEmailValid());
        }
    }

    @Transactional
    public UserEntity getUserEntity(KakaoUserInfo userInfo, KakaoTokenResponse kakaoTokenResponse) {
        Long currentTime = CommonUtil.getCurrentTimeToTimestamp();
        return userRepository.findById(userInfo.getId()).orElseGet(
                () -> userRepository.save(UserEntity.builder()
                        .id(userInfo.getId())
                        .email(!userInfo.getKakaoAccount().getEmailNeedsAgreement() ? userInfo.getKakaoAccount().getEmail() : null)
                        .profileNickname(!userInfo.getKakaoAccount().getProfileNicknameNeedsAgreement() ? userInfo.getKakaoAccount().getProfile().getNickname() : null)
                        .profileImageUrl(!userInfo.getKakaoAccount().getProfileImageNeedsAgreement() ? userInfo.getKakaoAccount().getProfile().getProfileImageUrl() : null)
                        .regDtime(stringToZonedDateTime(userInfo.getConnectedAt()))
                        .kakaoAccessTokenIssuanceTime(currentTime)
                        .kakaoAccessToken(kakaoTokenResponse.getAccessToken())
                        .kakaoAccessTokenExpiresIn(kakaoTokenResponse.getExpiresIn())
                        .kakaoRefreshTokenIssuanceTime(currentTime)
                        .kakaoRefreshToken(kakaoTokenResponse.getRefreshToken())
                        .kakaoRefreshTokenExpiresIn(kakaoTokenResponse.getRefreshTokenExpiresIn())
                        .build())
        );
    }

    private KakaoTokenResponse reissuedAccessToken(String refreshToken) {
        KakaoTokenResponse kakaoTokenResponse = null;
        try {
            kakaoTokenResponse = kaKaoApiClient.reissuedAccessToken(refreshToken).execute().body();
        } catch (IOException e) {
            log.error("reissued Kakao AccessToken Error: {}", e.getMessage());
        }
        return kakaoTokenResponse;
    }

    private KakaoUserInfo getUserInfo(String token) {
        KakaoUserInfo kakaoUserInfo = null;
        try {
            kakaoUserInfo = kaKaoApiClient.getUserInfo(token).execute().body();
        } catch (IOException e) {
            log.error("getUserInfo Error: {}", e.getMessage());
        }
        return kakaoUserInfo;
    }

    private KakaoTokenResponse getKakaoToken(String code)   {
        KakaoTokenResponse kakaoTokenResponse = null;
        try {
            kakaoTokenResponse = kaKaoApiClient.getToken(code).execute().body();
        } catch (IOException e) {
            log.error("getKakaoToken Error: {}", e.getMessage());
        }
        return kakaoTokenResponse;
    }

    private KakaoTokenInfo getKakaoAccessTokenInfo(String token) {
        KakaoTokenInfo kakaoTokenInfo = null;
        try {
            kakaoTokenInfo = kaKaoApiClient.getAccessTokenInfo(token).execute().body();
        } catch (IOException e) {
            log.error("getKakaoAccessTokenInfo Error: {}", e.getMessage());
        }
        return kakaoTokenInfo;
    }

    public KakaoLogout logout(String token) throws IOException {
        KakaoLogout kakaoLogout = null;
        try {
            kakaoLogout = kaKaoApiClient.logout(token).execute().body();
        } catch (IOException e) {
            log.error("Kakao Logout Error: {}", e.getMessage());
        }
        return kakaoLogout;
    }

    private ZonedDateTime stringToZonedDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        return ZonedDateTime.parse(date, formatter);
    }
}
