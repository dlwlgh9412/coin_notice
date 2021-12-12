package com.jjangchen.crawler.sender;

import com.jjangchen.common.repository.ExchangeNotificationRepository;
import com.jjangchen.crawler.event.NoticeCrawledEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailSender {
    private final ExchangeNotificationRepository notificationRepository;

    @EventListener
    void sendMail(NoticeCrawledEvent noticeCrawledEvent) {

    }
}
