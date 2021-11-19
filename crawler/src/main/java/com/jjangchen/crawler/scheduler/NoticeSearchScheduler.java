package com.jjangchen.crawler.scheduler;

import com.jjangchen.crawler.service.coinone.CoinoneNoticeService;
import com.jjangchen.crawler.service.upbit.UpbitNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
//@ConditionalOnProperty(prefix = "scheduler", name = "test", havingValue = "true")
public class NoticeSearchScheduler {
    private final UpbitNoticeService upbitService;
    private final CoinoneNoticeService coinoneNoticeService;

    @Scheduled(cron = "0 0 0/1 * * ?", zone = "Asia/Seoul")
    public void searchNotice() {
        upbitService.searchUpbit();
        coinoneNoticeService.searchCoinone();
        log.info("{} 데이터 Searching", ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
    }
}
