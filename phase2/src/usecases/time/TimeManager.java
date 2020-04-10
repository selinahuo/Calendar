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
}
