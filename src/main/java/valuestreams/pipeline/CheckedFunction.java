package valuestreams.pipeline;

@FunctionalInterface
interface CheckedFunction<T, R> {
    R apply(T input) throws Exception;
}
