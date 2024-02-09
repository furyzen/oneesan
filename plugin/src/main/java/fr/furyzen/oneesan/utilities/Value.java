package fr.furyzen.oneesan.utilities;

public class Value<T> {

    private final T value;

    protected Value(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public static <T> Value<T> of(T value) {
        return new Value<>(value);
    }
}
