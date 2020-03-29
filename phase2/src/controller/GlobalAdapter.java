package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GlobalAdapter {
    public static String dateToString(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return time.format(formatter);
    }

    public static LocalDateTime inputToDate(int year, int month, int day, int hour, int minute) {
        return LocalDateTime.of(year, month, day, hour, minute);
    }
}
