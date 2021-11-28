package com.jjangchen.common.entity;

import com.jjangchen.common.model.Social;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Embeddable
@Builder
@AllArgsConstructor
public class SocialAccountEntityId implements Serializable {
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "SOCIAL")
    private Social social;
}
