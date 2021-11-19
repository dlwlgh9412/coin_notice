package com.jjangchen.common.repository;

import com.jjangchen.common.entity.NoticeEntity;
import com.jjangchen.common.repository.custom.NoticeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long>, NoticeCustomRepository {
}
