package com.jjangchen.api_module.web;

import com.jjangchen.api_module.service.NoticeService;
import com.jjangchen.api_module.web.support.ApiRestSupport;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeRestApi extends ApiRestSupport {
    private final NoticeService noticeService;

    @GetMapping("/{exchange}")
    public ResponseEntity<?> findNotice(@PathVariable Exchange exchange, @RequestParam("kind") NoticeKind noticeKind, @RequestParam("keyword") String keyword,
                                       @RequestParam("page") int page) {
        return response(noticeService.findNotices(exchange, noticeKind, keyword, page));
    }

    /*
    @GetMapping
    public ResponseEntity<?> findNoticeToOverSea(@RequestParam Integer oversea, @PageableDefault(sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return response(noticeService.findNoticesToOverSea(oversea, pageable));
    }

     */
}
