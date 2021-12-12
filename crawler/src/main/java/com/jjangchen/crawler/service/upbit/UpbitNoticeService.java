package com.jjangchen.crawler.service.upbit;

import com.jjangchen.common.entity.NoticeEntity;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import com.jjangchen.common.repository.NoticeRepository;
import com.jjangchen.crawler.client.notice.upbit.UpbitNoticeRestClient;
import com.jjangchen.crawler.client.notice.upbit.dto.RequestUpbitNotice;
import com.jjangchen.crawler.client.notice.upbit.dto.UpbitNoticeData;
import com.jjangchen.crawler.client.notice.upbit.dto.UpbitNoticeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpbitNoticeService {
    private final UpbitNoticeRestClient restApiClient;
    private final NoticeRepository noticeRepository;

    @Transactional
    public Exchange searchUpbit() {
        RequestUpbitNotice reqeust = new RequestUpbitNotice("/notices", "general");
        BigDecimal standardId = noticeRepository.getMaxNoticeId(Exchange.UPBIT).orElse(BigDecimal.ZERO);
        List<NoticeEntity> noticeEntities = restApiClient.getNotice(reqeust).getData().getList().stream().filter(upbitNoticeInfo -> BigDecimal.valueOf(upbitNoticeInfo.getId()).compareTo(standardId) > 0)
                .map(upbitNoticeInfo -> NoticeEntity.builder()
                        .exchange(Exchange.UPBIT)
                        .noticeId(BigDecimal.valueOf(upbitNoticeInfo.getId()))
                        .noticeKind(upbitNoticeInfo.getTitle().contains("이벤트") ? NoticeKind.EVENT : NoticeKind.NOTICE)
                        .title(upbitNoticeInfo.getTitle())
                        .url("https://www.upbit.com/service_center/notice?id=" + upbitNoticeInfo.getId())
                        .createdAt(ZonedDateTime.parse(upbitNoticeInfo.getCreatedAt()))
                        .updDtime(ZonedDateTime.parse(upbitNoticeInfo.getCreatedAt()))
                        .build())
                .collect(Collectors.toList());
        return noticeRepository.saveAll(noticeEntities) != null ? Exchange.UPBIT : null;

        /*
        Response<UpbitNoticeResponse<UpbitNoticeData>> response = restApiClient.getNotices("general").execute();
            BigDecimal standardId = noticeRepository.getMaxNoticeId(Exchange.UPBIT).orElse(BigDecimal.ZERO);
            log.info("standardId: {}", standardId.toPlainString());
            if (response.isSuccessful()) {
                List<NoticeEntity> noticeEntities = response.body().getData().getList().stream().filter(upbitNoticeInfo -> BigDecimal.valueOf(upbitNoticeInfo.getId()).compareTo(standardId) > 0)
                        .map(upbitNoticeInfo -> NoticeEntity.builder()
                                .exchange(Exchange.UPBIT)
                                .noticeId(BigDecimal.valueOf(upbitNoticeInfo.getId()))
                                .noticeKind(upbitNoticeInfo.getTitle().contains("이벤트") ? NoticeKind.EVENT : NoticeKind.NOTICE)
                                .title(upbitNoticeInfo.getTitle())
                                .url("https://www.upbit.com/service_center/notice?id=" + upbitNoticeInfo.getId())
                                .createdAt(ZonedDateTime.parse(upbitNoticeInfo.getCreatedAt()))
                                .updDtime(ZonedDateTime.parse(upbitNoticeInfo.getCreatedAt()))
                                .build())
                        .collect(Collectors.toList());
                return noticeRepository.saveAll(noticeEntities);
            */
    }
}
