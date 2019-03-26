package valuestreams.pipeline;

import valuestreams.functions.CheckedFunction;
import valuestreams.operations.CheckedOperation;
import valuestreams.operations.OperationType;

public class CheckedPipe<T, R> implements CheckedOperation<T, R> {
    private CheckedFunction<T, R> mapper;

    public CheckedPipe(CheckedFunction<T, R> mapper) {
        this.mapper = mapper;
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
