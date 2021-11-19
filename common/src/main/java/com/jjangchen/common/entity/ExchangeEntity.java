package com.jjangchen.common.entity;

import com.jjangchen.common.model.Exchange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity(name = "TBL_EXCHANGE")
public class ExchangeEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private Exchange exchange;

    @Column(name = "OVERSEA")
    private Boolean oversea;

    @Column(name = "URL")
    private String url;
}
