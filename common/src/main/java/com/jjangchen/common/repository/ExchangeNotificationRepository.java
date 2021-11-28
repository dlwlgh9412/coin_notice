package com.jjangchen.common.repository;

import com.jjangchen.common.entity.ExchangeNotification;
import com.jjangchen.common.entity.ExchangeNotificationId;
import com.jjangchen.common.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeNotificationRepository extends JpaRepository<ExchangeNotification, Long> {
    Optional<ExchangeNotification> findByExchangeNotificationIdAndExchange(ExchangeNotificationId id, Exchange exchange);
}
