package dataaccess;

interface IFilter<T> {
    boolean filter(T item);
}
