package com.jjangchen.crawler.service;

import com.jjangchen.common.entity.ExchangeEntity;
import com.jjangchen.common.entity.NoticeEntity;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import com.jjangchen.common.repository.ExchangeRepository;
import com.jjangchen.common.repository.NoticeRepository;
import com.jjangchen.crawler.client.upbit.UpbitRestApiClient;
import com.jjangchen.crawler.client.upbit.model.UpbitNoticeData;
import com.jjangchen.crawler.client.upbit.model.UpbitNoticeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UpbitService {
    private final UpbitRestApiClient upbitRestApiClient;
    private final NoticeRepository noticeRepository;

    public UpbitService(UpbitRestApiClient upbitRestApiClient, NoticeRepository noticeRepository) {
        this.upbitRestApiClient = upbitRestApiClient;
        this.noticeRepository = noticeRepository;
    }
    @Transactional
    public void searchUpbit() {
        try {
            Response<UpbitNoticeResponse<UpbitNoticeData>> response = upbitRestApiClient.getNotices("general").execute();
            BigDecimal standardId = noticeRepository.getMaxNoticeId(Exchange.UPBIT.getExchange()).orElse(BigDecimal.ZERO);
            log.info("standardId: {}", standardId.toPlainString());
            if (response.isSuccessful()) {
                ExchangeEntity exchange = ExchangeEntity.builder().exchange(Exchange.UPBIT).oversea(false).url("https://upbit.com/service_center/notice").build();
                List<NoticeEntity> noticeEntities = response.body().getData().getList().stream().filter(upbitNoticeInfo -> BigDecimal.valueOf(upbitNoticeInfo.getId()).compareTo(standardId) > 0)
                        .map(upbitNoticeInfo -> NoticeEntity.builder()
                                .exchange(exchange)
                                .noticeId(BigDecimal.valueOf(upbitNoticeInfo.getId()))
                                .kind(upbitNoticeInfo.getTitle().contains("이벤트") ? NoticeKind.EVENT : NoticeKind.NOTICE)
                                .title(upbitNoticeInfo.getTitle())
                                .url("https://www.upbit.com/service_center/notice?id=" + upbitNoticeInfo.getId())
                                .createdAt(ZonedDateTime.parse(upbitNoticeInfo.getCreatedAt()))
                                .updDtime(ZonedDateTime.parse(upbitNoticeInfo.getCreatedAt()))
                                .build())
                        .collect(Collectors.toList());
                noticeRepository.saveAll(noticeEntities);
            }
        } catch (IOException e) {
            log.error("Upbit Notice search IOException: {}", e.getMessage());
        }
    }
}
