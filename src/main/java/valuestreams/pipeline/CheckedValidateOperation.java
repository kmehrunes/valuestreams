package valuestreams.pipeline;

import valuestreams.functions.CheckedPredicate;

public class CheckedValidateOperation<T> implements CheckedOperation<T, T> {
    private final CheckedPredicate<T> validator;

    public CheckedValidateOperation(CheckedPredicate<T> validator) {
        this.validator = validator;
    }

    @Override
    public OperationType getType() {
        return OperationType.VALIDATE;
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
