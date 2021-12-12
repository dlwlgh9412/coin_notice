package com.jjangchen.crawler.client.notice.coinone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoinoneNoticeDataWriter {
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("trading_level")
    private Integer tradingLevel;

    @JsonProperty("nickname")
    private String nickName;

    @JsonProperty("comment_count")
    private Long commentCount;

    @JsonProperty("thread_count")
    private Long threadCount;

    @JsonProperty("vote_count")
    private Long voteCount;

    @JsonProperty("level")
    private Integer level;

    @JsonProperty("signature")
    private String signature;

    @JsonProperty("user_type")
    private String userType;

    @JsonProperty("is_blocked")
    private Boolean isBlocked;
}
