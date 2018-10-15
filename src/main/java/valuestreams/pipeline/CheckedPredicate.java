package valuestreams.pipeline;

@FunctionalInterface
public interface CheckedPredicate<T> {
    Boolean test(T input) throws Exception;
}
