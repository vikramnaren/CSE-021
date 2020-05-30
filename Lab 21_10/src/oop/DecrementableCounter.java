package oop;

public class DecrementableCounter extends Counter {
    public int decrement() {
        int m = value();
        reset();
        for (int i = 0; i < m - 1; i++) {
            increment();
        }

        return value();
    }
}
