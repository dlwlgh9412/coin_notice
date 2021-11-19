package com.jjangchen.crawler.client.quote.Bithumb;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BithumbTickerData {
    @SerializedName("opening_price")
    private String openingPrice;

    @SerializedName("closing_price")
    private String closingPrice;

    @SerializedName("date")
    private String date; //timestamp
}
