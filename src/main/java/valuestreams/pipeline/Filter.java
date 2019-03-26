package valuestreams.pipeline;

import valuestreams.operations.Operation;
import valuestreams.operations.OperationType;

import java.util.function.Predicate;

public class Filter<T> implements Operation<T, T> {
    private final Predicate<T> validator;

    public Filter(Predicate<T> validator) {
        this.validator = validator;
    }

    @Override
    public T apply(T value) {
        return validator.test(value) ? value : null;
    }
}
