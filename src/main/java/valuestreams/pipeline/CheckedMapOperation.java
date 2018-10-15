package valuestreams.pipeline;

public class CheckedMapOperation<T, R> implements CheckedOperation<T, R> {
    private CheckedFunction<T, R> mapper;

    public CheckedMapOperation(CheckedFunction<T, R> mapper) {
        this.mapper = mapper;
    }

    @Override
    public OperationType getType() {
        return OperationType.MAP;
    }

    @Override
    public R apply(T value) {
        try {
            return mapper.apply(value);
        } catch (Exception ex) {
            return null;
        }
    }
}
