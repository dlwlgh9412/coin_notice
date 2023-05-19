package com.jjangchen.externalmodule.service;

import com.jjangchen.externalmodule.web.model.notice.Notice;
import com.jjangchen.externalmodule.web.model.notice.NoticePageResponse;
import com.jjangchen.common.entity.NoticeEntity;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import com.jjangchen.common.repository.custom.NoticeCustomRepository;
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

    public NoticePageResponse findNotices(Exchange exchange, NoticeKind noticeKind, String keyword, Integer page) {
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
}
