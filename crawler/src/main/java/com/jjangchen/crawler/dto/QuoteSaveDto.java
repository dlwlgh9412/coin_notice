package com.jjangchen.crawler.dto;

import com.jjangchen.common.entity.QuoteEntity;
import com.jjangchen.common.model.Exchange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class QuoteSaveDto {
    private Exchange exchange;
    private Long timeStamp;
    private String currency;
    private Long krw;
    private Double usdt;

    public QuoteEntity toEntity() {
        return QuoteEntity.builder()
                .exchange(exchange)
                .timeStamp(timeStamp)
                .currency(currency)
                .krw(krw)
                .usdt(usdt)
                .build();
    }
}
