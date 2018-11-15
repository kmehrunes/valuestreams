package valuestreams.operations;

public interface Operation<T, R> {
    OperationType getType();
    R apply(T value);
}
