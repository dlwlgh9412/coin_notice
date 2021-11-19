package com.jjangchen.externalmodule.service;

import com.jjangchen.common.entity.RefreshTokenEntity;
import com.jjangchen.common.entity.UserEntity;
import com.jjangchen.common.model.Role;
import com.jjangchen.common.repository.RefreshTokenRepository;
import com.jjangchen.externalmodule.web.exception.BearerException;
import com.jjangchen.externalmodule.web.model.JwtObject;
import com.jjangchen.externalmodule.web.exception.RefreshTokenNotExist;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@EnableConfigurationProperties
@PropertySource("classpath:properties/val-${spring.profiles.active}.properties")
@RequiredArgsConstructor
@Slf4j
@Service
public class JwtService {
    @Value("${jwtToken.secretKey}")
    private String secretKey;
    @Value("${jwtToken.issuer}")
    private String issuer;
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtObject reissuedAccessToken(String refreshToken) throws ExpiredJwtException {
        String token = refreshTokenRepository.findByToken(refreshToken).orElseThrow(RefreshTokenNotExist::new);
        Claims claims = parseToken(token);
        return JwtObject.builder()
                .accessToken(createAccessToken((long) claims.get("id"), (Role) claims.get("role")))
                .build();
    }

    public JwtObject getTokenObj(UserEntity userEntity) {
        String accessToken = createAccessToken(userEntity.getId(), userEntity.getRole());
        String refreshToken = createRefreshToken(userEntity.getId(), userEntity.getRole());
        return JwtObject.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenRepository.save(RefreshTokenEntity.builder()
                        .id(userEntity.getId())
                        .token(refreshToken)
                        .build()).getToken())
                .build();
    }

    public String createAccessToken(Long id, Role role) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setSubject("accessToken")
                .setAudience("user")
                .setExpiration(new Date(new Date().getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("id", id)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private String createRefreshToken(Long id, Role role) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setSubject("refreshToken")
                .setAudience("user")
                .setExpiration(new Date(new Date().getTime() + Duration.ofDays(30).toMillis()))
                .claim("id", id)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims parseToken(String token) throws SignatureException, ExpiredJwtException, BearerException {
        if (!token.startsWith("Bearer")) {
            throw new BearerException();
        }

        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(extractToken(token))
                .getBody();
    }

    private String extractToken(String token) {
        return token.substring("Bearer ".length());
    }
}
