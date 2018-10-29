package valuestreams.functions;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T input) throws Exception;
}
