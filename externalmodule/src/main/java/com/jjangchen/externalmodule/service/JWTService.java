package com.jjangchen.externalmodule.service;

import com.jjangchen.common.entity.RefreshTokenEntity;
import com.jjangchen.common.entity.SocialAccountEntity;
import com.jjangchen.common.model.Role;
import com.jjangchen.common.model.Social;
import com.jjangchen.common.repository.RefreshTokenRepository;
import com.jjangchen.externalmodule.web.exception.BearerException;
import com.jjangchen.externalmodule.dto.JWTResponseDto;
import com.jjangchen.externalmodule.web.exception.RefreshTokenNotExist;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@EnableConfigurationProperties
@PropertySource("classpath:properties/val-${spring.profiles.active}.properties")
@Slf4j
@RequiredArgsConstructor
@Component
public class JWTService {
    private static String secretKey;
    private static String issuer;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwtToken.secretKey}")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Value("${jwtToken.issuer}")
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public JWTResponseDto reissuedAccessToken(String refreshToken) throws ExpiredJwtException {
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken).orElseThrow(RefreshTokenNotExist::new);
        return JWTResponseDto.builder()
                .accessToken(createAccessToken(
                        refreshTokenEntity.getSocialAccountEntityId().getSocial(),
                        refreshTokenEntity.getSocialAccountEntityId().getId(),
                        Role.USER))
                .build();
    }

    public JWTResponseDto getTokenObj(SocialAccountEntity socialAccountEntity) {
        String accessToken = createAccessToken(socialAccountEntity.getSocialAccountEntityId().getSocial(), socialAccountEntity.getSocialAccountEntityId().getId(), Role.USER);
        String refreshToken = createRefreshToken(socialAccountEntity.getSocialAccountEntityId().getSocial(), socialAccountEntity.getSocialAccountEntityId().getId(), Role.USER);
        return JWTResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenRepository.save(RefreshTokenEntity.builder()
                        .socialAccountEntityId(socialAccountEntity.getSocialAccountEntityId())
                        .token(refreshToken)
                        .build()).getToken())
                .build();
    }

    private String createAccessToken(Social social, Long id, Role role) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setSubject("accessToken")
                .setAudience("user")
                .setExpiration(new Date(new Date().getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("social", social)
                .claim("id", id)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private String createRefreshToken(Social social, Long id, Role role) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setSubject("refreshToken")
                .setAudience("user")
                .setExpiration(new Date(new Date().getTime() + Duration.ofDays(30).toMillis()))
                .claim("social", social)
                .claim("id", id)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static Claims parseToken(String token) throws SignatureException, ExpiredJwtException, BearerException {
        if (!token.startsWith("Bearer")) {
            throw new BearerException();
        }
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(extractToken(token))
                .getBody();
    }

    private static String extractToken(String token) {
        return token.substring("Bearer ".length());
    }
}
