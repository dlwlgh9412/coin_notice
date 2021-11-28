package com.jjangchen.common.repository;

import com.jjangchen.common.entity.SocialAccountEntity;
import com.jjangchen.common.entity.SocialAccountEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialAccountEntityRepository extends JpaRepository<SocialAccountEntity, SocialAccountEntityId> {
}
