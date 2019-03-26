package valuestreams;

import valuestreams.functions.CheckedFunction;
import valuestreams.functions.CheckedPredicate;
import valuestreams.functions.MultiArgsFunction;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A generic value wrapper which provides basic
 * validation and mapping functionality.
 * @param <T> The type of the encapsulated value.
 */
public class Value<T> extends AbstractValue<T> {

    protected Value(T value) {
        super(value);
    }

    protected Value() { super(); }

    public static <R> Value<R> of(R value) {
        return new Value<>(value);
    }

    public static <R> Value<R> empty() {
        return new Value<>();
    }

    /**
     * Applies a predicate on the value, and empties
     * it if the predicate returned false.
     * @param validator A predicate whose type matches
     *                  that of the value.
     * @return The same instance.
     */
    public Value<T> validate(Predicate<T> validator) {
        Objects.requireNonNull(validator);

        if (!isEmpty()) {
            if (!validator.test(value)) {
                this.value = null;
            }
        }

        return this;
    }

    /**
     * Applies a predicate on the value, and empties
     * it if the predicate returned false.
     * @param validator A predicate whose type matches
     *                  that of the value.
     * @return The same instance.
     */
    public Value<T> validateWithException(CheckedPredicate<T> validator) {
        Objects.requireNonNull(validator);

        if (!isEmpty()) {
            try {
                if (!validator.test(value)) {
                    this.value = null;
                }
            } catch (Exception ex) {
                this.value = null;
            }
        }

        return this;
    }

    /**
     * Applies a mapper on the value.
     * @param mapper The mapper to applied.
     * @param <R> The return type of the mapper.
     * @return A new instance which contains the
     * mapped value.
     */
    public <R> Value<R> map(Function<T, R> mapper) {
        Objects.requireNonNull(mapper);
        return isEmpty() ? Value.empty() : Value.of(mapper.apply(this.value));
    }

    /**
     * Applies a mapper on the value.
     * @param mapper The mapper to applied.
     * @param <R> The return type of the mapper.
     * @return A new instance which contains the
     * mapped value.
     */
    public <T2, R> Value<R> map(MultiArgsFunction.TwoArgs<T, T2, R> mapper, T2 arg2) {
        Objects.requireNonNull(mapper);
        return isEmpty() ? Value.empty() : Value.of(mapper.apply(this.value, arg2));
    }

    /**
     * Applies a mapper on the value.
     * @param mapper The mapper to applied.
     * @param <R> The return type of the mapper.
     * @return A new instance which contains the
     * mapped value.
     */
    public <T2, T3, R> Value<R> map(MultiArgsFunction.ThreeArgs<T, T2, T3, R> mapper, T2 arg2, T3 arg3) {
        Objects.requireNonNull(mapper);
        return isEmpty() ? Value.empty() : Value.of(mapper.apply(this.value, arg2, arg3));
    }

    /**
     * Applies a mapper on the value.
     * @param mapper The mapper to applied.
     * @param <R> The return type of the mapper.
     * @return A new instance which contains the
     * mapped value.
     */
    public <T2, T3, T4, R> Value<R> map(MultiArgsFunction.FourArgs<T, T2, T3, T4, R> mapper, T2 arg2, T3 arg3, T4 arg4) {
        Objects.requireNonNull(mapper);
        return isEmpty() ? Value.empty() : Value.of(mapper.apply(this.value, arg2, arg3, arg4));
    }

    /**
     * Applies a mapper on the value.
     * @param mapper The mapper to applied.
     * @param <R> The return type of the mapper.
     * @return A new instance which contains the
     * mapped value.
     */
    public <T2, T3, T4, T5, R> Value<R> map(MultiArgsFunction.FiveArgs<T, T2, T3, T4, T5, R> mapper, T2 arg2, T3 arg3,
                                            T4 arg4, T5 arg5) {
        Objects.requireNonNull(mapper);
        return isEmpty() ? Value.empty() : Value.of(mapper.apply(this.value, arg2, arg3, arg4, arg5));
    }

    /**
     * Applies a mapper on the value.
     * @param mapper The mapper to applied.
     * @param <R> The return type of the mapper.
     * @return A new instance which contains the
     * mapped value.
     */
    public <T2, T3, T4, T5, T6, R> Value<R> map(MultiArgsFunction.SixArgs<T, T2, T3, T4, T5, T6, R> mapper, T2 arg2, T3 arg3,
                                                T4 arg4, T5 arg5, T6 arg6) {
        Objects.requireNonNull(mapper);
        return isEmpty() ? Value.empty() : Value.of(mapper.apply(this.value, arg2, arg3, arg4, arg5, arg6));
    }

    /**
     * Applies a mapper on the value.
     * @param mapper The mapper to applied.
     * @param <R> The return type of the mapper.
     * @return A new instance which contains the
     * mapped value.
     */
    public <R> Value<R> mapWithException(CheckedFunction<T, R> mapper) {
        Objects.requireNonNull(mapper);
        try {
            return isEmpty() ? Value.empty() : Value.of(mapper.apply(this.value));
        } catch (Exception ex) {
            return Value.empty();
        }
    }

    /**
     * The value-stream equivalent of equals().
     * @param other The object to compare against.
     * @return This instance
     */
    public Value<T> isEqualTo(T other) {
        return validate(v -> v.equals(other));
    }
}
