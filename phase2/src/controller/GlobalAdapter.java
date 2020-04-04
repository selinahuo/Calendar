package controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class GlobalAdapter {
    public static LocalDateTime stringToDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            return LocalDateTime.now();
        }
    }

    public static LocalDate stringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            return LocalDate.now();
        }
    }

    public static Month intToMonth(int month) {
        return Month.of(month);
    }

    public static DayOfWeek stringToWeekDay(String weekDay) {
        switch (weekDay) {
            case "m":
                return DayOfWeek.MONDAY;
            case "tu":
                return DayOfWeek.TUESDAY;
            case "w":
                return DayOfWeek.WEDNESDAY;
            case "th":
                return DayOfWeek.THURSDAY;
            case "f":
                return DayOfWeek.FRIDAY;
            case "sa":
                return DayOfWeek.SATURDAY;
            default:
                return DayOfWeek.SUNDAY;
        }
    }

    public static String dateToString(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return time.format(formatter);
    }

    public static LocalDateTime inputToDate(int year, int month, int day, int hour, int minute) {
        try {
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (Exception e) {
            return LocalDateTime.now();
        }
    }
}
