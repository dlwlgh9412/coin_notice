package com.jjangchen.crawler.service.upbit;

import com.jjangchen.common.converter.DateConverter;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.repository.QuoteRepository;
import com.jjangchen.crawler.client.UpbitRestApiService;
import com.jjangchen.crawler.client.quote.upbit.*;
import com.jjangchen.crawler.client.quote.upbit.candleStick.RequestUpbitCandleStick;
import com.jjangchen.crawler.client.quote.upbit.candleStick.UpbitCandleStickData;
import com.jjangchen.crawler.client.quote.upbit.candleStick.UpbitCandleStickInterval;
import com.jjangchen.crawler.dto.QuoteSaveDto;
import com.jjangchen.crawler.util.QuoteUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpbitQuoteService {
    private final UpbitRestApiService restApiService;
    private final RequestUpbitTicker requestUpbitTicker = RequestUpbitTicker.builder().build();
    private final QuoteRepository quoteRepository;
    public void candleStickByOneMinute() {
        RequestUpbitCandleStick requestUpbitCandleStick = RequestUpbitCandleStick.builder()
                .market(UpbitMarket.KRWBTC.getMarket())
                .interval(UpbitCandleStickInterval.OneMinute.getInterval())
                .count(1)
                .build();
        try {
            Response<List<UpbitCandleStickData>> response = restApiService.getCandleStick(requestUpbitCandleStick.toPath(), requestUpbitCandleStick.toQueryMap()).execute();
            if(response.isSuccessful()) {
                UpbitCandleStickData data = response.body().get(0);
                log.info("TimeStamp: {}", DateConverter.stringToEpoch(data.getCandleDateTimeKst()));
                log.info("opening Price: {}", Double.valueOf(data.getOpeningPrice().toString()));
            } else {
                log.error("UpbitQuoteService Error: {}", response.errorBody().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Runnable getBTCTicker(Long timeStamp) {
        return new Runnable() {
            @Override
            public void run() {
                requestUpbitTicker.setUpbitMarket(UpbitMarket.KRWBTC);
                try {
                    Response<List<UpbitTickerResponse>> response = restApiService.getTicker(requestUpbitTicker.getUpbitMarket().getMarket()).execute();
                    if (response.isSuccessful()) {
                        QuoteSaveDto saveDto = QuoteSaveDto.builder()
                                .exchange(Exchange.UPBIT)
                                .timeStamp(timeStamp)
                                .currency(response.body().get(0).getMarket())
                                .krw(QuoteUtil.doubleToLong(response.body().get(0).getTrade_price()))
                                .build();
                        quoteRepository.save(saveDto.toEntity());
                    } else {
                        log.error("Upbit Quote Error: {}", response.errorBody().string());
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
                requestUpbitTicker.setUpbitMarket(UpbitMarket.KRWETH);
                try {
                    Response<List<UpbitTickerResponse>> response = restApiService.getTicker(requestUpbitTicker.getUpbitMarket().getMarket()).execute();
                    if (response.isSuccessful()) {
                        QuoteSaveDto saveDto = QuoteSaveDto.builder()
                                .exchange(Exchange.UPBIT)
                                .timeStamp(timeStamp)
                                .currency(response.body().get(0).getMarket())
                                .krw(QuoteUtil.doubleToLong(response.body().get(0).getTrade_price()))
                                .build();
                        quoteRepository.save(saveDto.toEntity());
                    } else {
                        log.error("Upbit Quote Error: {}", response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
