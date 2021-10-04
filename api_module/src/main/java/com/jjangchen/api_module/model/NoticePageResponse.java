package com.jjangchen.api_module.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticePageResponse {
    @JsonProperty("total_size")
    private Long totalSize;

    @JsonProperty("total_page")
    private Long totalPage;

    @JsonProperty("current_size")
    private Long currentSize;

    @JsonProperty("current_page")
    private Long currentPage;

    @JsonProperty("notice_list")
    private List<Notice> noticeList;
}
