package dataclasses;

public class Sextuple<T, U, V, W, X, Y> {
    private T first;
    private U second;
    private V third;
    private W fourth;
    private X fifth;
    private Y sixth;

    public Sextuple(T first, U second, V third, W fourth, X fifth, Y sixth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
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

    public Y getSixth() { return sixth; }
}
