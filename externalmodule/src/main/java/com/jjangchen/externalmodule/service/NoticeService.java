package com.jjangchen.externalmodule.service;

import com.jjangchen.common.entity.UserEntity;
import com.jjangchen.common.entity.UserExchangeNotification;
import com.jjangchen.common.repository.UserExchangeNotificationRepository;
import com.jjangchen.common.repository.UserRepository;
import com.jjangchen.externalmodule.web.model.notice.Notice;
import com.jjangchen.externalmodule.web.model.notice.NoticePageResponse;
import com.jjangchen.common.entity.NoticeEntity;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import com.jjangchen.common.repository.custom.NoticeCustomRepository;
import com.jjangchen.externalmodule.web.exception.EmailNullException;
import com.jjangchen.externalmodule.web.exception.UserNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeCustomRepository noticeRepository;
    private final UserRepository userRepository;
    private final UserExchangeNotificationRepository userExchangeNotificationRepository;
    private final JwtService jwtService;

    public NoticePageResponse findNotices(Exchange exchange, NoticeKind noticeKind, String keyword, int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<NoticeEntity> noticeEntities = noticeRepository.findNotices(exchange, noticeKind, keyword, pageRequest);
        NoticePageResponse result = NoticePageResponse.builder()
                .totalPage((long) noticeEntities.getTotalPages())
                .totalSize((noticeEntities.getTotalElements()))
                .currentPage((long) noticeEntities.getPageable().getPageNumber())
                .currentSize((long) noticeEntities.getPageable().getPageSize())
                .noticeList(noticeEntities.getContent().stream().map(notice -> {
                    return Notice.builder()
                            .noticeId(notice.getNoticeId())
                            .exchange(notice.getExchange())
                            .noticeKind(notice.getKind())
                            .title(notice.getTitle())
                            .url(notice.getUrl())
                            .updatedAt(notice.getUpdDtime())
                            .createdAt(notice.getCreatedAt())
                            .build();
                }).collect(Collectors.toList()))
                .build();

        result.setCode(200);
        return result;
    }

    public Boolean setNotification(String token, Exchange exchange, Boolean param) throws EmailNullException, UserNotFoundException, SignatureException, ExpiredJwtException {
        Claims claims = jwtService.parseToken(token);
        Long id = (long) claims.get("id");
        UserEntity userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(userEntity.getEmail() == null)
            throw new EmailNullException();

        UserExchangeNotification userExchangeNotification = userExchangeNotificationRepository.findByUserAndExchange(userEntity, exchange).orElseGet(() -> UserExchangeNotification.builder()
                .exchange(exchange)
                .user(userEntity)
                .notification(param)
                .build());

        return userExchangeNotificationRepository.save(userExchangeNotification).getNotification();
    }
}
