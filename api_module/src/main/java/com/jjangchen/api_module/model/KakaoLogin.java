package com.jjangchen.api_module.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoLogin {
    private Long id;
    private String token;
    private String nickname;
    private String email;
}
