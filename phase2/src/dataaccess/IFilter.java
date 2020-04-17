package dataaccess;

/**
 * Interface to filter by entities
 * @param <T> type of entity to filter
 */
interface IFilter<T> {
    boolean filter(T item);
}
