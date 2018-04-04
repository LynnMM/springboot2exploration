package org.lynn.springboot2.exploration.reactor;

import reactor.core.publisher.Flux;

public class ReduceDemo {
    /**
     * reduce 和 reduceWith 操作符对流中包含的所有元素进行累积操作，得到一个包含计算结果的 Mono 序列
     */

    /**
     * 对流中的元素进行相加操作，结果为 5050
     */
    public static void reduce() {
        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
    }

    /**
     * 也是进行相加操作，不过通过一个 Supplier 给出了初始值为 100，所以结果为 5150。
     */
    public static void reduceWith() {
        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);
    }
}
