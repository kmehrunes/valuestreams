package valuestreams.pipeline;

import valuestreams.functions.CheckedPredicate;
import valuestreams.operations.CheckedOperation;
import valuestreams.operations.OperationType;

public class CheckedFilter<T> implements CheckedOperation<T, T> {
    private final CheckedPredicate<T> validator;

    public CheckedFilter(CheckedPredicate<T> validator) {
        this.validator = validator;
    }

    @Override
    public T apply(T value) {
        try {
            return validator.test(value) ? value : null;
        } catch (Exception ex) {
            return null;
        }
    }
}
