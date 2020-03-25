package usecases.time;

import java.time.LocalDateTime;

public class TimeManager {
    static private LocalDateTime time = LocalDateTime.now();
    static private boolean changed = false;

    public LocalDateTime getTime() {
        if (!changed) {
            time = LocalDateTime.now();
        }
        return time;
    }

    public void setTime(LocalDateTime time) {
        changed = true;
        this.time = time;
    }

    public void reset() {
        changed = false;
    }

    public void addHours(long hours) {
        changed = true;
        time.plusHours(hours);
    }

    public void addDays(long days) {
        changed = true;
        time.plusDays(days);
    }

    public void addMonths(long months) {
        changed = true;
        time.plusMonths(months);
    }

    public void addYears(long years) {
        changed = true;
        time.plusYears(years);
    }
}
