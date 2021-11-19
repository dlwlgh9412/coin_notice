package com.jjangchen.common.repository;

import com.jjangchen.common.entity.UserEntity;
import com.jjangchen.common.entity.UserExchangeNotification;
import com.jjangchen.common.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserExchangeNotificationRepository extends JpaRepository<UserExchangeNotification, Long> {
    Optional<UserExchangeNotification> findByUserAndExchange(UserEntity user, Exchange exchange);
}
