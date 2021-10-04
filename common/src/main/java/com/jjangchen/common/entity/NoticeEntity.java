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
@AllArgsConstructor
@Getter
@Builder
@Entity(name = "TBL_NOTICE")
public class NoticeEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOTICE_ID")
    private BigDecimal noticeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exchange")
    private ExchangeEntity exchange;

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
        return this.exchange.getExchange();
    }

    public Boolean getOversea() {
        return this.exchange.getOversea();
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
