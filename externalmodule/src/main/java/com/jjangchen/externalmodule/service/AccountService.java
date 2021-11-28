package com.jjangchen.externalmodule.service;

import com.jjangchen.common.entity.SocialAccountEntity;
import com.jjangchen.common.entity.SocialAccountEntityId;
import com.jjangchen.common.model.Social;
import com.jjangchen.common.repository.AccountRepository;
import com.jjangchen.common.repository.SocialAccountEntityRepository;
import com.jjangchen.externalmodule.client.kakao.model.KakaoTokenResponse;
import com.jjangchen.externalmodule.client.kakao.model.KakaoUserInfo;
import com.jjangchen.externalmodule.dto.KakaoAccountSaveDto;
import com.jjangchen.externalmodule.web.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//@Slf4j
//@Service
//@RequiredArgsConstructor
public class AccountService {
    /*
    public AccountEntity getAccountEntity(Long hashCode, Long socialId) throws AccountNotFoundException {
        return accountRepository.findById(hashCode / socialId).orElseThrow(AccountNotFoundException::new);
    }

     */

    /*
    @Transactional
    public AccountEntity insertAccountEntity(AccountSaveDto accountSaveDto) {
        return accountRepository.findByEmail(accountSaveDto.getEmail()).orElseGet(() ->
                accountRepository.save(accountSaveDto.toEntity()));
    }

     */

    /*
    public Boolean isExistAccount(String email) {
        return accountRepository.findByEmail(email).isPresent();
    }

     */

}
