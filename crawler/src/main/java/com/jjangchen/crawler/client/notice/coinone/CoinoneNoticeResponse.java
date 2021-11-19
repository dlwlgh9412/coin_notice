package com.jjangchen.crawler.client.notice.coinone;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoinoneNoticeResponse {
    @SerializedName("count")
    private Long count;

    @SerializedName("next")
    private String next;

    @SerializedName("previous")
    private String previous;

    @SerializedName("results")
    private List<CoinoneNoticeData> coinoneNoticeData;
}
