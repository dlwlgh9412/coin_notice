package com.jjangchen.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime entityVal) {
        if(entityVal == null)
            entityVal = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        if(!entityVal.getZone().equals(ZoneId.of("Asia/Seoul")))
            entityVal = entityVal.withZoneSameInstant(ZoneId.of("Asia/Seoul"));

        return Timestamp.from(entityVal.toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dataBaseVal) {
        if(dataBaseVal == null)
            return ZonedDateTime.now(ZoneId.systemDefault()).minusMonths(1);
        LocalDateTime localDateTime = dataBaseVal.toLocalDateTime();

        return localDateTime.atZone(ZoneId.systemDefault());
    }
}
