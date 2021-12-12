package com.jjangchen.crawler.client.notice.coinone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoinoneNoticeData {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("card_category")
    private String cardCategory;

    @JsonProperty("get_absolute_url")
    private String getAbsoluteUrl;

    @JsonProperty("created_by")
    private CoinoneNoticeDataWriter writer;

    @JsonProperty("flagged_content")
    private CoinoneNoticeFlaggedContent flaggedContent;

    @JsonProperty("vote_type_by_current_user")
    private String voteTypeByCurrentUser;

    @JsonProperty("content_type")
    private Long contentType;

    @JsonProperty("board")
    private String board;

    @JsonProperty("vote_count")
    private Long voteCount;

    @JsonProperty("title")
    private String title;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("created_at")
    private String createdAt;
    
    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("thumbnail_original")
    private String thumbnailOriginal;

    @JsonProperty("notice_type")
    private String notice_type;
}
