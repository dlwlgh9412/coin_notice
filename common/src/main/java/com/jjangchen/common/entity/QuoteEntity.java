package com.jjangchen.common.entity;

import com.jjangchen.common.model.Exchange;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TBL_QUOTE")
@NoArgsConstructor
@Getter
public class QuoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EXCHANGE")
    @Enumerated(EnumType.STRING)
    private Exchange exchange;

    @Column(name = "TIME_STAMP")
    private Long timeStamp;

    @Column(name = "CURRENCY")
    private String currency;


    @Column(name = "KRW")
    private Long krw;

    @Column(name = "USDT")
    private Double uddt;

    @Builder
    public QuoteEntity(Exchange exchange, Long timeStamp, String currency, Long krw, Double usdt) {
        this.exchange = exchange;
        this.timeStamp = timeStamp;
        this.currency = currency;
        this.krw = krw;
        this.uddt = usdt;
    }
}
