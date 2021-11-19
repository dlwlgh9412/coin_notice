package com.jjangchen.crawler;

import com.jjangchen.common.entity.ExchangeEntity;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.meta.Exhaustive;
import java.util.Arrays;
import java.util.List;

@Slf4j
@ComponentScan(basePackages = {"com.jjangchen.common", "com.jjangchen.crawler"})
@EnableJpaRepositories(basePackages = "com.jjangchen.common")
@EntityScan(basePackages = {"com.jjangchen.common", "com.jjangchen.crawler"})
@EnableScheduling
@SpringBootApplication
public class CrawlerApplication {
    private final Environment environment;
    private final ExchangeRepository exchangeRepository;

    public CrawlerApplication(Environment environment, ExchangeRepository exchangeRepository) {
        this.environment = environment;
        this.exchangeRepository = exchangeRepository;
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CrawlerApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent() {
        List<ExchangeEntity> exchangeEntityList = List.of(ExchangeEntity.builder()
                .exchange(Exchange.UPBIT)
                .oversea(false)
                .url("https://www.upbit.com")
                .build(), ExchangeEntity.builder()
                .exchange(Exchange.COINONE)
                .oversea(false)
                .url("https://www.coinone.co.kr/")
                .build(), ExchangeEntity.builder()
                .exchange(Exchange.BINANCE)
                .oversea(true)
                .url("https://www.binance.com")
                .build(), ExchangeEntity.builder()
                .exchange(Exchange.COINBASE)
                .oversea(true)
                .url("https://www.coinbase.com")
                .build(), ExchangeEntity.builder()
                .exchange(Exchange.BITHUMB)
                .oversea(false)
                .url("https://www.bithumb.com")
                .build());
        exchangeRepository.saveAll(exchangeEntityList);
        log.info("applicationReady Profiles:{}", Arrays.toString(environment.getActiveProfiles()));
    }
}
