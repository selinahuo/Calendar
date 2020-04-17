package dataaccess;

/**
 * Interface to edit entities
 * @param <T> type of entity to edit
 */
interface IEdit<T> {
    void edit(T item);
}
