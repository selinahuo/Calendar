package usecases.time;

import java.time.LocalDateTime;

public class TimeManager {
    static private LocalDateTime time = LocalDateTime.now();
    static private boolean changed = false;

    public static LocalDateTime getTime() {
        if (!changed) {
            time = LocalDateTime.now();
        }
        return time;
    }

    public static void setTime(LocalDateTime time) {
        changed = true;
        TimeManager.time = time;
    }

    public static void reset() {
        changed = false;
        time = LocalDateTime.now();
    }

    public static void addHours(long hours) {
        changed = true;
        time.plusHours(hours);
    }

    public static void addDays(long days) {
        changed = true;
        time.plusDays(days);
    }

    public static void addWeeks(long weeks) {
        changed = true;
        time.plusWeeks(weeks);
    }

    public static void addMonths(long months) {
        changed = true;
        time.plusMonths(months);
    }

    public static void addYears(long years) {
        changed = true;
        time.plusYears(years);
    }
}
