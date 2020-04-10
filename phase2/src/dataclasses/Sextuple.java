package dataclasses;

/**
 * Data class for six pieces of data.
 * Mainly used to avoid casts and for type safety.
 * @param <T> type of first data
 * @param <U> type of second data
 * @param <V> type of third data
 * @param <W> type of fourth data
 * @param <X> type of fifth data
 * @param <Y> type of sixth data
 */
public class Sextuple<T, U, V, W, X, Y> {
    private T first;
    private U second;
    private V third;
    private W fourth;
    private X fifth;
    private Y sixth;

    /**
     * Construct a sextuple with six datum
     * @param first first data
     * @param second second data
     * @param third third data
     * @param fourth fourth data
     * @param fifth fifth data
     * @param sixth sixth data
     */
    public Sextuple(T first, U second, V third, W fourth, X fifth, Y sixth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
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

    /**
     * Get third data
     * @return third data
     */
    public V getThird() {
        return third;
    }

    /**
     * Get fourth data
     * @return fourth data
     */
    public W getFourth() {
        return fourth;
    }

    /**
     * Get fifth data
     * @return fifth data
     */
    public X getFifth() {
        return fifth;
    }

    /**
     * Get sixth data
     * @return sixth data
     */
    public Y getSixth() {
        return sixth;
    }
}
