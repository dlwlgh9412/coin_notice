package com.jjangchen.common.repository;

import com.jjangchen.common.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<String> findByToken(String refreshToken);
}
