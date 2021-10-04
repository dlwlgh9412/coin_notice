package com.jjangchen.crawler;

import com.jjangchen.common.entity.ExchangeEntity;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.repository.ExchangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@Slf4j
@EnableJpaRepositories(basePackages = "com.jjangchen.common")
@EntityScan(basePackages = {"com.jjangchen.common", "com.jjangchen.crawler"})
@EnableScheduling
@SpringBootApplication
public class NoticeCrawlerApplication {
    private final Environment environment;

    public NoticeCrawlerApplication(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(NoticeCrawlerApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent() {
        log.info("applicationReady Profiles:{}", Arrays.toString(environment.getActiveProfiles()));
    }
}
