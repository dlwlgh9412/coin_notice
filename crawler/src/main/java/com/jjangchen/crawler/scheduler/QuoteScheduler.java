package com.jjangchen.crawler.scheduler;

import com.jjangchen.crawler.service.binance.BinanceQuoteService;
import com.jjangchen.crawler.service.bithumb.BithumbQuoteService;
import com.jjangchen.crawler.service.coinbase.CoinbaseQuoteService;
import com.jjangchen.crawler.service.coinone.CoinoneQuoteService;
import com.jjangchen.crawler.service.upbit.UpbitQuoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Slf4j
@Component
public class QuoteScheduler {
    private final BinanceQuoteService binanceQuoteService;
    private final BithumbQuoteService bithumbQuoteService;
    private final CoinbaseQuoteService coinbaseQuoteService;
    private final UpbitQuoteService upbitQuoteService;
    private final CoinoneQuoteService coinoneQuoteService;
    private final TaskExecutor executor;

    public QuoteScheduler(BinanceQuoteService binanceQuoteService,
                          BithumbQuoteService bithumbQuoteService,
                          CoinbaseQuoteService coinbaseQuoteService,
                          UpbitQuoteService upbitQuoteService,
                          CoinoneQuoteService coinoneQuoteService,
                          @Qualifier("taskExecutor") TaskExecutor taskExecutor) {
        this.binanceQuoteService = binanceQuoteService;
        this.bithumbQuoteService = bithumbQuoteService;
        this.coinbaseQuoteService = coinbaseQuoteService;
        this.upbitQuoteService = upbitQuoteService;
        this.coinoneQuoteService = coinoneQuoteService;
        this.executor = taskExecutor;
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void getQuote() {
        Long timeStamp = (Timestamp.valueOf(LocalDateTime.now()).getTime() / 100) * 100;
        executor.execute(binanceQuoteService.getBTCTicker(timeStamp));
        executor.execute(binanceQuoteService.getETHTicker(timeStamp));
        executor.execute(bithumbQuoteService.getBTCTicker(timeStamp));
        executor.execute(bithumbQuoteService.getETHTicker(timeStamp));
        executor.execute(coinbaseQuoteService.getBTCTicker(timeStamp));
        executor.execute(coinbaseQuoteService.getETHTicker(timeStamp));
        executor.execute(upbitQuoteService.getBTCTicker(timeStamp));
        executor.execute(upbitQuoteService.getETHTicker(timeStamp));
        executor.execute(coinoneQuoteService.getBTCTicker(timeStamp));
        executor.execute(coinoneQuoteService.getETHTicker(timeStamp));
    }
}
