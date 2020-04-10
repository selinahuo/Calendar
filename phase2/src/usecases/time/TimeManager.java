package usecases.time;

import java.time.LocalDateTime;

/**
 * Manager responsible for managing the application time
 */
public class TimeManager {
    static private LocalDateTime time = LocalDateTime.now();
    static private boolean changed = false;

    /**
     * Get the application's current time. This time does not change if it is manually set.
     * @return application's current time
     */
    public static LocalDateTime getTime() {
        if (!changed) {
            time = LocalDateTime.now();
        }
        return time;
    }

    /**
     * Set the application's time. Time stop until reset.
     * @param time datetime to set
     */
    public static void setTime(LocalDateTime time) {
        changed = true;
        TimeManager.time = time;
    }

    /**
     * Reset application's time to system and which progresses regularly.
     */
    public static void reset() {
        changed = false;
        time = LocalDateTime.now();
    }
}
