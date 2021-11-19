package com.jjangchen.common.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    public static Long stringToEpoch(String date) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss";
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp.getTime();
    }
}
