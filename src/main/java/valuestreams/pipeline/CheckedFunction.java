package valuestreams.pipeline;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T input) throws Exception;
}
