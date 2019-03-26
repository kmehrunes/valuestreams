package valuestreams.operations;

@FunctionalInterface
public interface Operation<T, R> {
    R apply(T value);
}
