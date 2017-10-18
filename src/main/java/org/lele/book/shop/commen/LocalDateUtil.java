package org.lele.book.shop.commen;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * author  shuly
 * date    17-4-20.
 */
public class LocalDateUtil {

    public static final DateTimeFormatter IOS_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static long transMilliSecond(String dateStr) {
        return transMilliSecond(transLocalData(dateStr));
    }
    public static LocalDate transLocalData(String dateStr) {
        return LocalDate.parse(dateStr, IOS_DATE_FORMATTER);
    }



    public static LocalDate transLocalData(long milliSecond) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliSecond), ZoneId.systemDefault()).toLocalDate();
    }
    public static String transBasicString(long milliSecond) {
        return transBasicString(transLocalData(milliSecond));
    }



    public static String transBasicString(LocalDate localDate) {
        return localDate.format(IOS_DATE_FORMATTER);
    }

    public static long transMilliSecond(LocalDate localDate) {
        return localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    public static LocalDate convertDate(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate firstDayOfMonth() {
        return LocalDate.now().withDayOfMonth(1);
    }

    public static LocalDate firstDayIfWeekChina() {
        return LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }
}