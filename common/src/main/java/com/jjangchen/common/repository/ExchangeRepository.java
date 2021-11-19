package com.jjangchen.common.repository;

import com.jjangchen.common.entity.ExchangeEntity;
import com.jjangchen.common.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeEntity, Exchange> {
    Optional<ExchangeEntity> findByExchange(Exchange exchange);
}
