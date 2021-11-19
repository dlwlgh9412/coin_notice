package com.jjangchen.common.entity;

import com.jjangchen.common.converter.ZonedDateTimeConverter;
import com.jjangchen.common.model.Role;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "TBL_USER")
public class UserEntity {
    @Id
    private Long id;

    @Column(name = "PROFILE_NICKNAME")
    private String profileNickname;

    @Column(name = "PROFILE_IMAGE_URL")
    private String  profileImageUrl;

    private String email;

    @Column(name = "REG_DTIME", updatable = false)
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime regDtime;

    @Column(name = "ROLE")
    @Enumerated
    private Role role;

    @Column(name = "KAKAO_ACCESS_TOKEN_ISSUANCE_TIME")
    private Long kakaoAccessTokenIssuanceTime;

    @Column(name = "KAKAO_ACCESS_TOKEN")
    private String kakaoAccessToken;

    @Column(name = "KAKAO_ACCESS_TOKEN_EXPIRES_IN")
    private Integer kakaoAccessTokenExpiresIn;

    @Column(name = "KAKAO_REFRESH_TOKEN_ISSUANCE_TIME")
    private Long kakaoRefreshTokenIssuanceTime;

    @Column(name = "KAKAO_REFRESH_TOKEN")
    private String kakaoRefreshToken;

    @Column(name = "KAKAO_REFRESH_TOKEN_EXPIRES_IN")
    private Integer kakaoRefreshTokenExpiresIn;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserExchangeNotification> userExchangeNotificationList = new ArrayList<>();

    public UserEntity reissuedKakaoToken(String kakaoAccessToken, Integer kakaoAccessTokenExpiresIn, String kakaoRefreshToken, Integer kakaoRefreshTokenExpiresIn) {
        this.kakaoAccessToken = kakaoAccessToken;
        this.kakaoAccessTokenExpiresIn = kakaoAccessTokenExpiresIn;
        if(kakaoRefreshToken != null) {
            this.kakaoRefreshToken = kakaoRefreshToken;
            this.kakaoRefreshTokenExpiresIn = kakaoRefreshTokenExpiresIn;
        }

        return this;
    }
}
