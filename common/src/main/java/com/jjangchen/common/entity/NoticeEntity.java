package com.jjangchen.common.entity;

import com.jjangchen.common.converter.ZonedDateTimeConverter;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Getter
@Entity(name = "TBL_NOTICE")
public class NoticeEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOTICE_ID")
    private BigDecimal noticeId;

    @Column(name = "EXCHANGE")
    @Enumerated(EnumType.STRING)
    private Exchange exchange;

    @Column(name = "KIND")
    @Enumerated(EnumType.STRING)
    private NoticeKind kind;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "URL")
    private String url;

    @Column(name = "CREATED_AT")
    private ZonedDateTime createdAt;

    @Column(name = "REG_TIME", unique = false)
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime regDtime;

    @Column(name = "UPD_TIME")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime updDtime;

    public Exchange getExchange() {
        return this.exchange;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Builder
    public NoticeEntity(BigDecimal noticeId, Exchange exchange, NoticeKind noticeKind, String title, String url, ZonedDateTime createdAt, ZonedDateTime regDtime, ZonedDateTime updDtime) {
        this.noticeId = noticeId;
        this.exchange = exchange;
        this.kind = noticeKind;
        this.title = title;
        this.url = url;
        this.createdAt = createdAt;
        this.regDtime = regDtime;
        this.updDtime = updDtime;
    }
}
