package com.jjangchen.crawler.service.binance;

import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.repository.QuoteRepository;
import com.jjangchen.crawler.client.BinanceRestApiService;
import com.jjangchen.crawler.client.quote.binance.BinanceSymbol;
import com.jjangchen.crawler.client.quote.binance.BinanceTickerResponse;
import com.jjangchen.crawler.client.quote.binance.RequestBinanceTicker;
import com.jjangchen.crawler.client.quote.binance.candleStick.BinanceCandleStickInterval;
import com.jjangchen.crawler.client.quote.binance.candleStick.RequestBinanceCandleStick;
import com.jjangchen.crawler.dto.QuoteSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceQuoteService {
    private final BinanceRestApiService restApiService;
    private final QuoteRepository quoteRepository;

    public Runnable getBTCTicker(Long timeStamp) {
        return new Runnable() {
            @Override
            public void run() {
                RequestBinanceTicker requestBTCTicker = RequestBinanceTicker.builder()
                        .symbol(BinanceSymbol.BTCUSDT.getSymbol())
                        .build();
                try {
                    Response<BinanceTickerResponse> response = restApiService.getTicker(requestBTCTicker.getSymbol()).execute();
                    if(response.isSuccessful()) {
                        QuoteSaveDto saveDto = QuoteSaveDto.builder()
                                .exchange(Exchange.BINANCE)
                                .currency(response.body().getSymbol())
                                .timeStamp(timeStamp)
                                .usdt(Double.valueOf(response.body().getLastPrice()))
                                .build();
                        quoteRepository.save(saveDto.toEntity());
                    } else {
                        log.error("Binance BTC Quote Error: {}", response.errorBody().string());
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
                RequestBinanceTicker requestETHTicker = RequestBinanceTicker.builder()
                        .symbol(BinanceSymbol.ETHUSDT.getSymbol())
                        .build();
                try {
                    Response<BinanceTickerResponse> response = restApiService.getTicker(requestETHTicker.getSymbol()).execute();
                    if(response.isSuccessful()) {
                        QuoteSaveDto saveDto = QuoteSaveDto.builder()
                                .exchange(Exchange.BINANCE)
                                .currency(response.body().getSymbol())
                                .timeStamp(timeStamp)
                                .usdt(Double.valueOf(response.body().getLastPrice()))
                                .build();
                        quoteRepository.save(saveDto.toEntity());
                    } else {
                        log.error("Binance Quote Error: {}", response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void candleStickByOneMinute() {
        RequestBinanceCandleStick requestBinanceCandleStick = RequestBinanceCandleStick.builder()
                .symbol("BTCUSDT")
                .interval(BinanceCandleStickInterval.OneMinute)
                .limit(1)
                .build();
        try {
            Response<List<List<Object>>> response = restApiService.getCandlesStick(requestBinanceCandleStick.toQueryMap()).execute();
            if(response.isSuccessful()) {
                List<List<Object>> objects = response.body();
                log.info("Time Stamp: {}", Double.valueOf(objects.get(0).get(0).toString()).longValue());
                log.info("Opening Price: {}", objects.get(0).get(1));
            } else {
                log.error(response.errorBody().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
