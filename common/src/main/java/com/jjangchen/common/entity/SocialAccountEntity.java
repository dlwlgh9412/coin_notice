package com.jjangchen.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "TBL_SOCIAL_ACCOUNT")
public class SocialAccountEntity {
    @EmbeddedId
    private SocialAccountEntityId socialAccountEntityId;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "ACCESS_TOKEN_ISSUANCE_TIME")
    private Long accessTokenIssuanceTime;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @Column(name = "ACCESS_TOKEN_EXPIRES_IN")
    private Long accessTokenExpiresIn;

    @Column(name = "REFRESH_TOKEN_ISSUANCE_TIME")
    private Long refreshTokenIssuanceTime;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name = "REFRESH_TOKEN_EXPIRES_IN")
    private Long refreshTokenExpiresIn;
}
