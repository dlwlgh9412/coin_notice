package com.jjangchen.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@AllArgsConstructor
@Builder
@Table(name = "TBL_REFRESH_TOKEN")
public class RefreshTokenEntity {
    @Id
    private SocialAccountEntityId socialAccountEntityId;

    @Column(columnDefinition = "TEXT", name = "TOKEN")
    private String token;
}
