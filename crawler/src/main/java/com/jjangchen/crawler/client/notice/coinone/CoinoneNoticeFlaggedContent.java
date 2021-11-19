package com.jjangchen.crawler.client.notice.coinone;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoinoneNoticeFlaggedContent {
    @SerializedName("status")
    private String status;
}
