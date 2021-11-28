package com.jjangchen.common.entity;

import com.jjangchen.common.converter.ZonedDateTimeConverter;
import com.jjangchen.common.model.Role;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@Entity(name = "TBL_ACCOUNT")

 */
public class AccountEntity {
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "KAKAO_ID", unique = true)
    private Long kakaoHashCode;

    @Column(name = "NAVER_ID", unique = true)
    private Long naverHashCode;

    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "IMAGE_URL")
    private String  imageUrl;

    @Column(name = "REG_DTIME", updatable = false)
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime regDtime;

    @Column(name = "ROLE")
    @Enumerated
    private Role role;

     */

    /*
    @OneToMany(mappedBy = "accountEntity", cascade = CascadeType.ALL)
    private List<UserExchangeNotification> userExchangeNotificationList = new ArrayList<>();
     */
}