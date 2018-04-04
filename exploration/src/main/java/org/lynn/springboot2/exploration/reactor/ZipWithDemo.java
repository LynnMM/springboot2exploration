package org.lynn.springboot2.exploration.reactor;

import reactor.core.publisher.Flux;

public class ZipWithDemo {
    /**
     * zipWith 操作符把当前流中的元素与另外一个流中的元素按照一对一的方式进行合并。
     */

    /**
     * 在合并时可以不做任何处理，由此得到的是一个元素类型为 Tuple2 的流
     */
    public static void zipWith() {
        Flux.just("a", "b").zipWith(Flux.just("c", "d")).subscribe(System.out::println);
    }

    /**
     * 通过一个 BiFunction 函数对合并的元素进行处理，所得到的流的元素类型为该函数的返回值。
     */
    public static void zipWithCombined() {
        Flux.just("a", "b").zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
            .subscribe(System.out::println);
    }
}
