package com.jjangchen.common.util;

import com.jjangchen.common.exception.AccessTokenExpiresException;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class CommonUtil {
    public static Long getCurrentTimeToTimestamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp.getTime() / 1000;
    }

    public static void checkExpiresKakaoToken(Long issuanceTime, Integer expiresIn, String msg) throws AccessTokenExpiresException {
        if(getCurrentTimeToTimestamp() > issuanceTime + expiresIn)
            throw new AccessTokenExpiresException(msg);
    }
}
