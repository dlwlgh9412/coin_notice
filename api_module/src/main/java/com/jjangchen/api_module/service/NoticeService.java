package com.jjangchen.api_module.service;

import com.jjangchen.api_module.model.Notice;
import com.jjangchen.api_module.model.NoticePageResponse;
import com.jjangchen.common.entity.NoticeEntity;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import com.jjangchen.common.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticePageResponse findNotices(Exchange exchange, NoticeKind noticeKind, String keyword, int page) {
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.Direction.DESC, "upd_time");
        Page<NoticeEntity> noticeEntities = noticeRepository.findNotices(exchange.toString(), noticeKind.toString(), keyword, pageRequest);
        return NoticePageResponse.builder()
                .totalPage(Long.valueOf(noticeEntities.getTotalPages()))
                .totalSize((noticeEntities.getTotalElements()))
                .currentPage(Long.valueOf(noticeEntities.getPageable().getPageNumber()))
                .currentSize(Long.valueOf(noticeEntities.getPageable().getPageSize()))
                .noticeList(noticeEntities.getContent().stream().map(notice -> {
                    return Notice.builder()
                            .noticeId(notice.getNoticeId())
                            .exchange(notice.getExchange())
                            .oversea(notice.getOversea())
                            .noticeKind(notice.getKind())
                            .title(notice.getTitle())
                            .url(notice.getUrl())
                            .updatedAt(notice.getUpdDtime())
                            .createdAt(notice.getCreatedAt())
                            .build();
                }).collect(Collectors.toList()))
                .build();
    }

    /*
    public NoticePageResponse findNoticesToOverSea(Integer oversea, Pageable pageable) {

    }
     */
}
