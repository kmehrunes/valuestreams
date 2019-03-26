package valuestreams.operations;

public class IdentityOperation<T> implements Operation<T, T> {
    @Override
    public T apply(T value) {
        return value;
    }
}
