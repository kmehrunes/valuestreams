package valuestreams.pipeline;

import valuestreams.Value;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Pipeline<I, O> {
    private List<Operation<?, ?>> operations;

    private Pipeline() {}

    public static <T> Pipeline<T, T> input(Class<T> inputType) {
        Pipeline<T, T> chain = new Pipeline<>();
        chain.operations = Collections.singletonList(new IdentityOperation<T>());
        return chain;
    }

    public static <T> Pipeline<T, T> input() {
        Pipeline<T, T> chain = new Pipeline<>();
        chain.operations = Collections.singletonList(new IdentityOperation<T>());
        return chain;
    }

    public static <T, R> Pipeline<T, R> input(Operation<T, R> operation) {
        Pipeline<T, R> chain = new Pipeline<>();
        chain.operations = Collections.singletonList(operation);
        return chain;
    }

    public <R> Pipeline<I, R> chain(Operation<O, R> pipe) {
        Pipeline<I, R> extendedPipeline = new Pipeline<>();
        extendedPipeline.operations = new ArrayList<>(operations);
        extendedPipeline.operations.add(pipe);
        return extendedPipeline;
    }

    public <R> Pipeline<I, R> map(Function<O, R> mapper) {
        return chain(new MapOperation<>(mapper));
    }

    public Pipeline<I, O> validate(Predicate<O> validator) {
        return chain(new ValidateOperation<>(validator));
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Value<O> apply(I input) {
        Object source = input;
        Object target = null;

        for (Operation p : operations) {
            target = p.apply(source);
            source = target;

            if (source == null) {
                break;
            }
        }

        return target != null ? Value.of((O) target) : Value.empty();
    }
}
