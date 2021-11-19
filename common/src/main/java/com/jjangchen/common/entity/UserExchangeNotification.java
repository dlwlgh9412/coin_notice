package com.jjangchen.common.entity;

import com.jjangchen.common.model.Exchange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
@Table(name = "TBL_USER_EXCHANGE_NOTIFICATION")
public class UserExchangeNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "EXCHANGE")
    private Exchange exchange;

    @Column(name = "NOTIFICATION")
    protected Boolean notification;

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }
}
