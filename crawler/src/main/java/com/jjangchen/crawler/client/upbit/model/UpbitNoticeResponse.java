package com.jjangchen.crawler.client.upbit.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpbitNoticeResponse<T> {
    @SerializedName("success")
    private String success;

    @SerializedName("data")
    private T data;
}
