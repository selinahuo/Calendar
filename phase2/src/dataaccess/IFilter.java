package dataaccess;

public interface IFilter<T> {
    boolean filter(T item);
}
