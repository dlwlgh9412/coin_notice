package com.jjangchen.crawler.service.bithumb;

import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.repository.QuoteRepository;
import com.jjangchen.crawler.client.BithumbRestApiClient;
import com.jjangchen.crawler.client.quote.Bithumb.BithumbCurrency;
import com.jjangchen.crawler.client.quote.Bithumb.BithumbTickerResponse;
import com.jjangchen.crawler.client.quote.Bithumb.RequestBithumbTicker;
import com.jjangchen.crawler.dto.QuoteSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class BithumbQuoteService  {
    private final BithumbRestApiClient restApiClient;
    private final QuoteRepository quoteRepository;

    public Runnable getBTCTicker(Long timeStamp) {
        return new Runnable() {
            @Override
            public void run() {
                RequestBithumbTicker data = RequestBithumbTicker.builder()
                        .orderCurrency(BithumbCurrency.BTC.getCurrency())
                        .paymentCurrency(BithumbCurrency.KRW.getCurrency())
                        .build();
                try {
                    Response<BithumbTickerResponse> response = restApiClient.getTicker(data).execute();
                    if(response.isSuccessful()) {
                        QuoteSaveDto saveDto = QuoteSaveDto.builder()
                                .exchange(Exchange.BITHUMB)
                                .currency(BithumbCurrency.BTC.getCurrency())
                                .krw(Long.valueOf(response.body().getData().getClosingPrice()))
                                .timeStamp(timeStamp)
                                .build();
                        quoteRepository.save(saveDto.toEntity());
                    } else {
                        log.error("Bithumb BTC Quote Error: {}", response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public Runnable getETHTicker(Long timeStamp) {
        return new Runnable() {
            @Override
            public void run() {
                RequestBithumbTicker data = RequestBithumbTicker.builder()
                        .orderCurrency(BithumbCurrency.ETH.getCurrency())
                        .paymentCurrency(BithumbCurrency.KRW.getCurrency())
                        .build();
                try {
                    Response<BithumbTickerResponse> response = restApiClient.getTicker(data).execute();
                    if(response.isSuccessful()) {
                        QuoteSaveDto saveDto = QuoteSaveDto.builder()
                                .exchange(Exchange.BITHUMB)
                                .currency(BithumbCurrency.ETH.getCurrency())
                                .krw(Long.valueOf(response.body().getData().getClosingPrice()))
                                .timeStamp(timeStamp)
                                .build();
                        quoteRepository.save(saveDto.toEntity());
                    } else {
                        log.error("Bithumb BTC Quote Error: {}", response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
