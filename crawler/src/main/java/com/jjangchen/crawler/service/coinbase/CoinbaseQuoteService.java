package com.jjangchen.crawler.service.coinbase;

import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.repository.QuoteRepository;
import com.jjangchen.crawler.client.CoinbaseRestApiClient;
import com.jjangchen.crawler.client.quote.coinbase.*;
import com.jjangchen.crawler.dto.QuoteSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoinbaseQuoteService {
    private final CoinbaseRestApiClient restApiClient;
    private final QuoteRepository quoteRepository;

    public Runnable getBTCTicker(Long timeStamp) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Response<CoinbaseCurrencyData> response = restApiClient.getCurrency(CoinBaseMarket.BTC.getMarket()).execute();
                    if(response.isSuccessful()) {
                        QuoteSaveDto saveDto = QuoteSaveDto.builder()
                                .currency(CoinBaseMarket.BTC.getMarket())
                                .timeStamp(timeStamp)
                                .exchange(Exchange.COINBASE)
                                .usdt(Double.valueOf(response.body().getCoinbaseCurrency().getCoinbaseCurrencyRates().getUsdt()))
                                .build();
                        quoteRepository.save(saveDto.toEntity());
                    } else {
                        log.error("CoinbaseQuoteService getExchangeRates() Error: {}", response.errorBody().string());
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
                try {
                    Response<CoinbaseCurrencyData> response = restApiClient.getCurrency(CoinBaseMarket.ETH.getMarket()).execute();
                    if(response.isSuccessful()) {
                        QuoteSaveDto saveDto = QuoteSaveDto.builder()
                                .currency(CoinBaseMarket.BTC.getMarket())
                                .timeStamp(timeStamp)
                                .exchange(Exchange.COINBASE)
                                .usdt(Double.valueOf(response.body().getCoinbaseCurrency().getCoinbaseCurrencyRates().getUsdt()))
                                .build();
                        quoteRepository.save(saveDto.toEntity());
                    } else {
                        log.error("CoinbaseQuoteService getExchangeRates() Error: {}", response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
    /*
    private void getTime() {
        try {
            log.info("coinbase getTime Start: {}", LocalDateTime.now());
            Response<CoinbaseCurrentTimeData> response = restApiClient.getCurrentTime().execute();
            log.info("coinbase getTime end: {}", LocalDateTime.now());

            if(response.isSuccessful()) {
                log.info("iso: {}", response.body().getCoinbaseCurrentTime().getIso());
                log.info("epoch: {}", response.body().getCoinbaseCurrentTime().getEpoch());
            } else {
                log.error("CoinbaseQuoteService getTime() Error: {}", response.errorBody().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void candleStickByOneMinute() {
        RequestCoinbaseCandleStick requestCoinbaseCandleStick = RequestCoinbaseCandleStick.builder()
                .market(CoinBaseMarket.BTCUSDT)
                .interval(CoinbaseCandleStickIntervals.OneMinute)
                .build();
        try {
            Response<List<List<Object>>> response = restApiClient.getCandles(requestCoinbaseCandleStick).execute();
            if(response.isSuccessful()) {
                List<List<Object>> body = response.body();
                log.info("time: {}", body.get(0).get(0));
                log.info("openPrice: {}", body.get(0).get(3));
            } else {
                log.error("CoinbaseQuoteService candleStickByOneMinute() Error: {}", response.errorBody().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     */
}
