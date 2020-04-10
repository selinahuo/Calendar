package dataclasses;

/**
 * Data class for storing two pieces of data
 * Mainly used to avoid casts and for type safety.
 * @param <T> type of first data
 * @param <U> type of second data
 */
public class Tuple<T, U> {
    private T first;
    private U second;

    /**
     * Construct Tuple with two datum
     * @param first first data
     * @param second second data
     */
    public Tuple(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Get first data
     * @return first data
     */
    public T getFirst() {
        return first;
    }

    /**
     * Get second data
     * @return second data
     */
    public U getSecond() {
        return second;
    }
}
