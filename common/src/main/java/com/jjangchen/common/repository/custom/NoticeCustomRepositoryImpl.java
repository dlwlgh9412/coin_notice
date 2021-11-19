package com.jjangchen.common.repository.custom;

import com.jjangchen.common.entity.NoticeEntity;
import com.jjangchen.common.model.Exchange;
import com.jjangchen.common.model.NoticeKind;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.jjangchen.common.entity.QNoticeEntity.*;

@RequiredArgsConstructor
public class NoticeCustomRepositoryImpl implements NoticeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Optional<BigDecimal> getMaxNoticeId(Exchange exchange) {
        return Optional.ofNullable(jpaQueryFactory.select(noticeEntity.noticeId.max()).from(noticeEntity).where(noticeEntity.exchange.eq(exchange)).fetchOne());
    }

    @Override
    public Page<NoticeEntity> findNotices(Exchange exchange, NoticeKind noticeKind, String keyword, Pageable pageable) {
        QueryResults<NoticeEntity> result = jpaQueryFactory
                .selectFrom(noticeEntity)
                .where(eqExchange(exchange), eqKind(noticeKind), eqKeyword(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(noticeEntity.updDtime.desc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression eqExchange(Exchange exchange) {
        if(exchange.getExchange().equals("ALL"))
            return null;
        if(StringUtils.hasText(noticeEntity.exchange.toString()))
            return noticeEntity.exchange.eq(exchange);
        return null;
    }

    private BooleanExpression eqKind(NoticeKind noticeKind) {
        if(noticeKind.getKind().equals("ALL"))
            return null;
        if(StringUtils.hasText(noticeKind.getKind()))
            return noticeEntity.kind.eq(noticeKind);
        return null;
    }

    private BooleanExpression eqKeyword(String keyword) {
        if(StringUtils.hasText(keyword)) {
            return noticeEntity.title.contains(keyword);
        }

        return null;
    }
}
