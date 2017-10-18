package org.lele.book.shop.commen;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * author  shuly
 * date    17-4-20.
 */
public class LocalDateTimeUtil {

    public static class Content{
        public static final DateTimeFormatter IOS_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        public static final DateTimeFormatter SIMPLE_DATA_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    }


    public static long transMilliSecond(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String transBasicString(LocalDateTime localDateTime) {
        return localDateTime.format(Content.IOS_DATE_TIME_FORMATTER);
    }


    public static LocalDateTime transLocalDataTime(long milliSecond) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSecond), ZoneId.systemDefault());
    }

    public static String transBasicString(long milliSecond) {
        return transLocalDataTime(milliSecond).format(Content.IOS_DATE_TIME_FORMATTER);
    }

    public static long transMilliSecond(String dateTimeStr) {
        return transMilliSecond(transLocalDataTime(dateTimeStr));
    }

    public static LocalDateTime transLocalDataTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, Content.IOS_DATE_TIME_FORMATTER);
    }



    public static String transString(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return localDateTime.format(formatter);
    }

    public static LocalDateTime convertDate(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }
}