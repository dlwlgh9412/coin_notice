package com.jjangchen.externalmodule.web;

import com.jjangchen.externalmodule.web.advice.ValidJwtToken;
import com.jjangchen.externalmodule.service.NoticeService;
import com.jjangchen.externalmodule.web.support.ApiRestSupport;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeRestApi extends ApiRestSupport {
    private final NoticeService noticeService;

    @ApiResponses({@ApiResponse(code = 200, message = "data: {}")})
    @ApiOperation(value = "거래소별로 공지사항을 페이징 처리하여 로드", notes = "")
    @GetMapping("/{exchange}")
    @ValidJwtToken
    public ResponseEntity<?> findNotice(HttpServletRequest request,
                                        @ApiParam(value = "거래소 이름", required = true) @PathVariable Exchange exchange,
                                        @ApiParam(value = "공지사항 종류") @RequestParam(value = "kind", required = true) NoticeKind noticeKind,
                                        @ApiParam(value = "검색어") @RequestParam(value = "keyword", required = false) String keyword,
                                        @ApiParam(value = "요청 페이지", required = true) @RequestParam("page") Integer page) {
        return response(noticeService.findNotices(exchange, noticeKind, keyword, page));
    }

    /*
    @PostMapping("/{exchange}")
    public ResponseEntity<?> setNotification(@RequestHeader("AccessToken") String token,
                                             @PathVariable Exchange exchange,
                                             @RequestParam("alarm") Boolean alarm) {
        return new ResponseEntity<>(noticeService.alarmSetting(token, exchange, alarm), HttpStatus.OK);
    }

     */
}
