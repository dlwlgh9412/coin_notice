package com.jjangchen.crawler.client.notice.coinone;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoinoneNoticeDataWriter {
    @SerializedName("uuid")
    private String uuid;

    @SerializedName("trading_level")
    private Integer tradingLevel;

    @SerializedName("nickname")
    private String nickName;

    @SerializedName("comment_count")
    private Long commentCount;

    @SerializedName("thread_count")
    private Long threadCount;

    @SerializedName("vote_count")
    private Long voteCount;

    @SerializedName("level")
    private Integer level;

    @SerializedName("signature")
    private String signature;

    @SerializedName("user_type")
    private String userType;

    @SerializedName("is_blocked")
    private Boolean isBlocked;
}
