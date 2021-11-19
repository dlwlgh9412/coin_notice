package com.jjangchen.crawler.service.coinone;

import com.jjangchen.common.entity.NoticeEntity;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import com.jjangchen.common.repository.NoticeRepository;
import com.jjangchen.crawler.client.CoinoneRestApiClient;
import com.jjangchen.crawler.client.notice.coinone.CoinoneNoticeResponse;
import com.jjangchen.crawler.client.notice.coinone.RequestCoinoneNotice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CoinoneNoticeService {
    private final CoinoneRestApiClient coinoneRestApiClient;
    private final NoticeRepository noticeRepository;

    @Transactional
    public void searchCoinone() {
        try {
            RequestCoinoneNotice request = new RequestCoinoneNotice(1, 20);
            Response<CoinoneNoticeResponse> response = coinoneRestApiClient.getNotices(request).execute();
            if(response.isSuccessful()) {
                BigDecimal noticeId = noticeRepository.getMaxNoticeId(Exchange.COINONE).orElse(BigDecimal.ZERO);
                List<NoticeEntity> noticeEntityList = response.body().getCoinoneNoticeData().stream().filter(notice -> BigDecimal.valueOf(notice.getId()).compareTo(noticeId) > 0)
                        .map(notice -> NoticeEntity.builder()
                                .noticeId(BigDecimal.valueOf(notice.getId()))
                                .title(notice.getTitle())
                                .exchange(Exchange.COINONE)
                                .noticeKind(notice.getNotice_type().contains("이벤트") ? NoticeKind.EVENT : NoticeKind.NOTICE)
                                .url("https://www.coinone.co.kr" + notice.getGetAbsoluteUrl())
                                .createdAt(ZonedDateTime.parse(notice.getCreatedAt()))
                                .updDtime(ZonedDateTime.parse(notice.getUpdatedAt()))
                                .build()
                        ).collect(Collectors.toList());
                noticeRepository.saveAll(noticeEntityList);
            } else {
                log.error("Coinone Notice Search Error: {}", response.errorBody().string());
            }
        } catch (IOException e) {
            log.error("Coinone Notice Search IOException {}", e.getMessage());
        }
    }
}
