package com.jjangchen.common.entity;

import com.jjangchen.common.model.Social;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Embeddable
public class ExchangeNotificationId implements Serializable {
    @Column(name = "ID")
    private Long id;

    @Enumerated
    @Column(name = "SOCIAL")
    private Social social;
}
