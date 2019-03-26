package valuestreams.pipeline;

import valuestreams.operations.Operation;
import valuestreams.operations.OperationType;

import java.util.function.Function;

public class Pipe<T, R> implements Operation<T, R> {
    private final Function<T, R> mapper;

    public Pipe(Function<T, R> mapper) {
        this.mapper = mapper;
    }

    @Override
    public R apply(T value) {
        return mapper.apply(value);
    }
}
