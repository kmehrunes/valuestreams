package valuestreams.operations;

import valuestreams.functions.MultiArgsFunction;

public class MultiArgOperation {
    public static  <T1, T2, R> Operation<T1, R> create(MultiArgsFunction.TwoArgs<T1, T2, R> function, T2 arg2) {
        return arg1 -> function.apply(arg1, arg2);
    }

    public static <T1, T2, T3, R> Operation<T1, R> create(MultiArgsFunction.ThreeArgs<T1, T2, T3, R> function,
                                                   T2 arg2, T3 arg3) {
        return arg1 -> function.apply(arg1, arg2, arg3);
    }
}
