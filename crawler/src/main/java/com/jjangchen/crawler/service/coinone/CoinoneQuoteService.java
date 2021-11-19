package com.jjangchen.crawler.service.coinone;

import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.repository.QuoteRepository;
import com.jjangchen.crawler.client.CoinoneRestApiClient;
import com.jjangchen.crawler.client.quote.coinone.CoinoneTickerResponse;
import com.jjangchen.crawler.client.quote.coinone.RequestCoinoneTicker;
import com.jjangchen.crawler.dto.QuoteSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class CoinoneQuoteService {
    private final CoinoneRestApiClient coinoneRestApiClient;
    private final QuoteRepository quoteRepository;

    public Runnable getBTCTicker(Long timestamp) {
        return new Runnable() {
            @Override
            public void run() {
                RequestCoinoneTicker request = new RequestCoinoneTicker("BTC");
                try {
                    Response<CoinoneTickerResponse> response = coinoneRestApiClient.getQuotes(request).execute();
                    if (response.isSuccessful()) {
                        QuoteSaveDto quote = QuoteSaveDto.builder()
                                .exchange(Exchange.COINONE)
                                .currency(request.getCurrency().toUpperCase())
                                .timeStamp(timestamp)
                                .krw(response.body().getLastPrice().longValue())
                                .build();
                        quoteRepository.save(quote.toEntity());
                    } else {
                        log.error("Coinone getBTCTicker Error: {}", response.errorBody().string());
                    }
                } catch (IOException e) {
                    log.error("Coinone getBTCTicker IOException: {}", e.getMessage());
                }
            }
        };
    }

    public Runnable getETHTicker(Long timestamp) {
        return new Runnable() {
            @Override
            public void run() {
                RequestCoinoneTicker request = new RequestCoinoneTicker("ETH");
                try {
                    Response<CoinoneTickerResponse> response = coinoneRestApiClient.getQuotes(request).execute();
                    if (response.isSuccessful()) {
                        QuoteSaveDto quote = QuoteSaveDto.builder()
                                .exchange(Exchange.COINONE)
                                .currency(request.getCurrency().toUpperCase())
                                .timeStamp(timestamp)
                                .krw(response.body().getLastPrice().longValue())
                                .build();
                        quoteRepository.save(quote.toEntity());
                    } else {
                        log.error("Coinone getBTCTicker Error: {}", response.errorBody().string());
                    }
                } catch (IOException e) {
                    log.error("Coinone getBTCTicker IOException: {}", e.getMessage());
                }
            }
        };
    }
}
