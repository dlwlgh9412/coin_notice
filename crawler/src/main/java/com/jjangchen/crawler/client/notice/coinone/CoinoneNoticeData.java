package com.jjangchen.crawler.client.notice.coinone;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CoinoneNoticeData {
    @SerializedName("id")
    private Long id;

    @SerializedName("card_category")
    private String cardCategory;

    @SerializedName("get_absolute_url")
    private String getAbsoluteUrl;

    @SerializedName("created_by")
    private CoinoneNoticeDataWriter writer;

    @SerializedName("flagged_content")
    private CoinoneNoticeFlaggedContent flaggedContent;

    @SerializedName("vote_type_by_current_user")
    private String voteTypeByCurrentUser;

    @SerializedName("content_type")
    private Long contentType;

    @SerializedName("board")
    private String board;

    @SerializedName("vote_count")
    private Long voteCount;

    @SerializedName("title")
    private String title;

    @SerializedName("summary")
    private String summary;

    @SerializedName("created_at")
    private String createdAt;
    
    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("thumbnail_original")
    private String thumbnailOriginal;

    @SerializedName("notice_type")
    private String notice_type;
}
