package com.jjangchen.common.repository;

import com.jjangchen.common.entity.ExchangeWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeWalletRepository extends JpaRepository<ExchangeWalletEntity, Long> {
    List<ExchangeWalletEntity> findByCrypto(String crypto);
    List<ExchangeWalletEntity> findByBlockChain(String blockChain);
}
