package com.jjangchen.crawler.scheduler;

import com.jjangchen.crawler.service.UpbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "scheduler", name = "test", havingValue = "true")
public class NoticeSearchScheduler {
    private final UpbitService upbitService;

    public NoticeSearchScheduler(UpbitService upbitService) {
        this.upbitService = upbitService;
    }

    //@Scheduled(cron = "0 5 * * * *", zone = "Asia/Seoul")
    @Scheduled(fixedDelay = 5000)
    public void searchNotice() {
        upbitService.searchUpbit();

        log.info("{} 데이터 Searching", ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
    }

}
