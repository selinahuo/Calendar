package dataclasses;

public class Quintuple<T, U, V, W, X> {
    private T first;
    private U second;
    private V third;
    private W fourth;
    private X fifth;

    public Quintuple(T first, U second, V third, W fourth, X fifth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public V getThird() {
        return third;
    }

    public W getFourth() {
        return fourth;
    }

    public X getFifth() {
        return fifth;
    }
}
