package org.lynn.springboot2.exploration.reactor;

import reactor.core.publisher.Flux;

public class BufferDemo {
    /**
     * buffer和bufferTimeout这两个操作符的作用是把当前流中的元素收集到集合中，并把集合对象作为流中的新元素。
     */

    /**
     * 在进行收集时可以指定不同的条件：所包含的元素的最大数量或收集的时间间隔。 方法 buffer()仅使用一个条件，而 bufferTimeout()可以同时指定两个条件。
     */
    public static void buffer() {
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
    }

    /**
     * 指定时间间隔时可以使用 Duration 对象或毫秒数，即使用 bufferMillis()或 bufferTimeoutMillis()两个方法。
     */
    public static void bufferMillis() {
        // Flux.intervalMillis(100).bufferMillis(1001).take(2).toStream().forEach(System.out::println);
    }

    /**
     * bufferUntil 会一直收集直到 Predicate 返回为 true。
     */
    public static void bufferUntil() {
        Flux.range(1, 10).bufferUntil(integer -> integer % 2 == 0).subscribe(System.out::println);
    }

    /**
     * bufferWhile 则只有当 Predicate 返回 true 时才会收集。一旦值为 false，会立即开始下一次收集。
     */
    public static void bufferWhile() {
        Flux.range(1, 10).bufferWhile(integer -> integer % 2 == 0).subscribe(System.out::println);
    }
}
