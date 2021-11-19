package com.jjangchen.crawler.client.quote.Bithumb;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BithumbTickerResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private BithumbTickerData data;
}
