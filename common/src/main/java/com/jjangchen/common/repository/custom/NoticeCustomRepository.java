package com.jjangchen.common.repository.custom;

import com.jjangchen.common.entity.NoticeEntity;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

public interface NoticeCustomRepository {
    Optional<BigDecimal> getMaxNoticeId(Exchange exchange);
    Page<NoticeEntity> findNotices(Exchange exchange, NoticeKind noticeKind, String keyword, Pageable pageable);

    /*
    @Query(value = "SELECT MAX(NOTICE_ID) FROM COIN_NOTICE.TBL_NOTICE INNER JOIN TBL_EXCHANGE TOE ON TBL_NOTICE.EXCHANGE = TOE.EXCHANGE WHERE TOE.EXCHANGE = ?1", nativeQuery = true)
    Optional<BigDecimal> getMaxNoticeId(String noticeExchange);

    @Query(value = "SELECT * FROM COIN_NOTICE.TBL_NOTICE WHERE EXCHANGE = ?1 AND KIND = ?2 AND TITLE LIKE %?3%", nativeQuery = true)
    Page<NoticeEntity> findNotices(String exchange, String noticeKind, String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM COIN_NOTICE.TBL_NOTICE WHERE EXCHANGE = ?1", nativeQuery = true)
    List<NoticeEntity> findAllByExchange(String exchange);
     */
}
