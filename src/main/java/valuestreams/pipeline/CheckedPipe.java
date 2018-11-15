package valuestreams.pipeline;

import valuestreams.functions.CheckedFunction;

public class CheckedPipe<T, R> implements CheckedOperation<T, R> {
    private CheckedFunction<T, R> mapper;

    public CheckedPipe(CheckedFunction<T, R> mapper) {
        this.mapper = mapper;
    }

    @Override
    public OperationType getType() {
        return OperationType.PIPE;
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
