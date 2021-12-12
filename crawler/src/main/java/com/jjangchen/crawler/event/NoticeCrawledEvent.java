package com.jjangchen.crawler.event;

import com.jjangchen.common.entity.NoticeEntity;
import com.jjangchen.common.model.Exchange;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeCrawledEvent {
    private List<Exchange> exchangeList;
}
