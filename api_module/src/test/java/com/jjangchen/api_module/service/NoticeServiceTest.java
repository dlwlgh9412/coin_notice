package com.jjangchen.api_module.service;

import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;
}
