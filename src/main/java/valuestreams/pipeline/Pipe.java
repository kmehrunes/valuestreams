package valuestreams.pipeline;

import java.util.function.Function;

public class Pipe<T, R> implements Operation<T, R> {
    private final Function<T, R> mapper;

    public Pipe(Function<T, R> mapper) {
        this.mapper = mapper;
    }

    @Override
    public OperationType getType() {
        return OperationType.PIPE;
    }

    @Override
    public R apply(T value) {
        return mapper.apply(value);
    }
}
