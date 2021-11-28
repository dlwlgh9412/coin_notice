package com.jjangchen.externalmodule.dto;

import com.jjangchen.common.entity.SocialAccountEntity;
import com.jjangchen.common.entity.SocialAccountEntityId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class KakaoAccountSaveDto {
    private SocialAccountEntityId id;
    private String email;
    private Long kakaoAccessTokenIssuanceTime;
    private String kakaoAccessToken;
    private Long kakaoAccessTokenExpiresIn;
    private Long kakaoRefreshTokenIssuanceTime;
    private String kakaoRefreshToken;
    private Long kakaoRefreshTokenExpiresIn;

    public SocialAccountEntity toEntity() {
        return SocialAccountEntity.builder()
                .socialAccountEntityId(id)
                .email(email)
                .accessTokenIssuanceTime(kakaoAccessTokenIssuanceTime)
                .accessToken(kakaoAccessToken)
                .accessTokenExpiresIn(kakaoAccessTokenExpiresIn)
                .refreshTokenIssuanceTime(kakaoRefreshTokenIssuanceTime)
                .refreshToken(kakaoRefreshToken)
                .refreshTokenExpiresIn(kakaoRefreshTokenExpiresIn)
                .build();
    }
}
