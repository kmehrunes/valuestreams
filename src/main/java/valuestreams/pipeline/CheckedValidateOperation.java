package valuestreams.pipeline;

import java.util.function.Predicate;

public class CheckedValidateOperation<T> implements CheckedOperation<T, T> {
    private final Predicate<T> validator;

    public CheckedValidateOperation(Predicate<T> validator) {
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
