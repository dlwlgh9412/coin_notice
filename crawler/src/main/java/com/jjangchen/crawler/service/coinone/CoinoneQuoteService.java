package com.jjangchen.crawler.service.coinone;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CoinoneQuoteService {
    /*
    private final CoinoneQuoteRestClient coinoneRestApiClient;
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

     */
}
