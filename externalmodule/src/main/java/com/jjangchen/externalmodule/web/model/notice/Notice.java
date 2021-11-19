package com.jjangchen.externalmodule.web.model.notice;

import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {
    private BigDecimal noticeId;

    private Exchange exchange;

    private NoticeKind noticeKind;

    private String title;

    private String url;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
