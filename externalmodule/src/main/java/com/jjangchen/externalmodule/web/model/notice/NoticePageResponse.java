package com.jjangchen.externalmodule.web.model.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticePageResponse implements NoticeResponse {
    private Integer code;

    private Long totalSize;

    private Long totalPage;

    private Long currentSize;

    private Long currentPage;

    private List<Notice> noticeList;

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }
}
