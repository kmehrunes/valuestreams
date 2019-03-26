package valuestreams.functions;

public class MultiArgsFunction {
    @FunctionalInterface
    public interface TwoArgs<T1, T2, R> {
        R apply(T1 arg1, T2 agr2);
    }

    @FunctionalInterface
    public interface ThreeArgs<T1, T2, T3, R> {
        R apply(T1 arg1, T2 agr2, T3 arg3);
    }

    @FunctionalInterface
    public interface FourArgs<T1, T2, T3, T4, R> {
        R apply(T1 arg1, T2 arg2, T3 arg3, T4 arg4);
    }

    @FunctionalInterface
    public interface FiveArgs<T1, T2, T3, T4, T5, R> {
        R apply(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5);
    }

    @FunctionalInterface
    public interface SixArgs<T1, T2, T3, T4, T5, T6, R> {
        R apply(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6);
    }
}
