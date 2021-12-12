package com.jjangchen.crawler.scheduler;

import com.jjangchen.common.entity.NoticeEntity;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.crawler.event.NoticeCrawledEvent;
import com.jjangchen.crawler.service.coinone.CoinoneNoticeService;
import com.jjangchen.crawler.service.upbit.UpbitNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
//@ConditionalOnProperty(prefix = "scheduler", name = "test", havingValue = "true")
public class NoticeSearchScheduler {
    private final UpbitNoticeService upbitService;
    private final CoinoneNoticeService coinoneNoticeService;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(cron = "0 0/1 * * * ?", zone = "Asia/Seoul")
    public void searchNotice() {
        List<Exchange> exchangeList = new ArrayList<>();
        exchangeList.add(upbitService.searchUpbit());
        exchangeList.add(coinoneNoticeService.searchCoinone());
        log.info("{} 데이터 Searching", ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        eventPublisher.publishEvent(new NoticeCrawledEvent(exchangeList));
    }
}
