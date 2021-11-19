package com.jjangchen.crawler.client.quote.upbit;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpbitTickerResponse {
    // 종목 구분 코드
    @SerializedName("market")
    private String market;

    // 최근거래일자(UTC)
    @SerializedName("trade_date")
    private String tradeDate;

    // 최근 거래 시각(UTC)
    @SerializedName("trade_time")
    private String tradeTime;

    // 최근 거래 일자(KST)
    @SerializedName("trade_date_kst")
    private String trade_date_kst;

    // 최근 거래 시각(KST)
    @SerializedName("trade_time_kst")
    private String trade_time_kst;

    // 시가
    @SerializedName("opening_price")
    private Double opening_price;

    // 고가
    @SerializedName("high_price")
    private Double high_price;

    // 저가
    @SerializedName("low_price")
    private Double low_price;

    // 종가
    @SerializedName("trade_price")
    private Double trade_price;

    // 전일 종가
    @SerializedName("prev_closing_price")
    private Double prev_closing_price;

    // EVEN: 보합 RISE: 상승 FALL: 하락
    @SerializedName("change")
    private String change;

    // 변화액의 절대값
    @SerializedName("change_price")
    private Double change_price;

    // 변화율의 절대값
    @SerializedName("change_rate")
    private Double change_rate;

    // 가장 최근 거래량
    @SerializedName("trade_volume")
    private Double trade_volume;

    // 누적 거래대금(UTC 0시 기준)
    @SerializedName("acc_trade_price")
    private Double acc_trade_price;

    // 24시간 누적 거래대금
    @SerializedName("acc_trade_price_24h")
    private Double acc_trade_price_24h;

    // 누적 거래량(UTC 0시 기준)
    @SerializedName("acc_trade_volume")
    private Double acc_trade_volume;

    // 24시간 누적 거래량
    @SerializedName("acc_trade_volume_24h")
    private Double acc_trade_volume_24h;

    // 타임스탬프
    @SerializedName("timestamp")
    private Long timestamp;
}
