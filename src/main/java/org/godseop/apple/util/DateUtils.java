package org.godseop.apple.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String getDateTimeStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        LocalDateTime now = LocalDateTime.now();

        return now.format(dateTimeFormatter);
    }

    public static String getDateStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        LocalDateTime now = LocalDateTime.now();

        return now.format(dateTimeFormatter);
    }

    public static String getFileNameStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

        LocalDateTime now = LocalDateTime.now();

        return now.format(dateTimeFormatter);
    }

}
