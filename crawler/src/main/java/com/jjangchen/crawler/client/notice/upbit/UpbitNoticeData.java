package com.jjangchen.crawler.client.notice.upbit;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UpbitNoticeData {
    @SerializedName("total_count")
    private Long totalCount;

    @SerializedName("total_page")
    private Long totalPages;

    @SerializedName("list")
    private List<UpbitNoticeInfo> list;

    @SerializedName("fixed_notices")
    private List<UpbitNoticeInfo> fixedNotices;
}
