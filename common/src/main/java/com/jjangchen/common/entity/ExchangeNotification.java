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
@Table(name = "TBL_ACCOUNT_EXCHANGE_NOTIFICATION")
public class ExchangeNotification {
    @EmbeddedId
    private ExchangeNotificationId exchangeNotificationId;

    /*
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountEntity accountEntity;
     */

    @Enumerated(EnumType.STRING)
    @Column(name = "EXCHANGE")
    private Exchange exchange;

    @Column(name = "NOTIFICATION")
    protected Boolean notification;

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }
}
