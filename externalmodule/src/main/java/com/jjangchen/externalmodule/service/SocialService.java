package com.jjangchen.externalmodule.service;

import com.jjangchen.common.entity.SocialAccountEntity;
import com.jjangchen.common.entity.SocialAccountEntityId;
import com.jjangchen.common.model.Social;
import com.jjangchen.common.repository.SocialAccountEntityRepository;
import com.jjangchen.externalmodule.client.kakao.KaKaoApiClient;
import com.jjangchen.externalmodule.client.kakao.model.KakaoLogout;
import com.jjangchen.externalmodule.client.kakao.model.KakaoTokenInfo;
import com.jjangchen.externalmodule.client.kakao.model.KakaoTokenResponse;
import com.jjangchen.externalmodule.client.kakao.model.KakaoUserInfo;
import com.jjangchen.externalmodule.dto.KakaoAccountSaveDto;
import com.jjangchen.externalmodule.dto.JWTResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Slf4j
@Service
public class SocialService {
    private final KaKaoApiClient kaKaoApiClient;
    private final SocialAccountEntityRepository socialAccountEntityRepository;
    private final JWTService jwtService;

    public JWTResponseDto requestKakaoToken(String code) {
        KakaoTokenResponse kakaoTokenResponse = getKakaoToken(code); // 카카오 액세스토큰 발급
        KakaoUserInfo kakaoUserInfo = getUserInfo(kakaoTokenResponse.getAccessToken());
        SocialAccountEntity socialAccountEntity = findAccount(kakaoUserInfo, kakaoTokenResponse);
        return jwtService.getTokenObj(socialAccountEntity);
    }

    public SocialAccountEntity findAccount(KakaoUserInfo userInfo, KakaoTokenResponse kakaoTokenResponse) {
        return socialAccountEntityRepository.findById(
                        SocialAccountEntityId.builder()
                                .id(userInfo.getId())
                                .social(Social.KAKAO).build())
                .orElseGet(() -> insertKakaoAccount(userInfo, kakaoTokenResponse));
    }

    private SocialAccountEntity insertKakaoAccount(KakaoUserInfo userInfo, KakaoTokenResponse kakaoTokenResponse) {
        KakaoAccountSaveDto accountSaveDto = KakaoAccountSaveDto.builder()
                .id(SocialAccountEntityId.builder().id(userInfo.getId()).social(Social.KAKAO).build())
                .kakaoAccessTokenIssuanceTime(kakaoTokenResponse.getKakaoAccessTokenIssuanceTime())
                .kakaoAccessToken(kakaoTokenResponse.getAccessToken())
                .kakaoAccessTokenExpiresIn(kakaoTokenResponse.getExpiresIn().longValue())
                .kakaoRefreshTokenIssuanceTime(kakaoTokenResponse.getKakaoRefreshTokenIssuanceTime())
                .kakaoRefreshToken(kakaoTokenResponse.getRefreshToken())
                .kakaoRefreshTokenExpiresIn(kakaoTokenResponse.getRefreshTokenExpiresIn().longValue())
                .build();
        return socialAccountEntityRepository.save(accountSaveDto.toEntity());
    }

    // 추가 동의항목
    /*
    @Transactional
    public void additionalItemAgreement(String accessToken, String code) throws SignatureException, ExpiredJwtException, UserNotFoundException {
        Claims claims = jwtService.parseToken(accessToken);
        UserEntity userEntity = userRepository.findByEmail(claims.get("email").toString()).orElseThrow(UserNotFoundException::new);
        try {
            CommonUtil.checkExpiresKakaoToken(userEntity.getKakaoAccessTokenIssuanceTime(), userEntity.getKakaoAccessTokenExpiresIn(), "Kakao AccessToken Expired");
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

     */

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

    private KakaoTokenResponse getKakaoToken(String code) {
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
