package org.lynn.springboot2.exploration.reactor;

import reactor.core.publisher.Flux;

public class WindowDemo {

    /**
     * window 操作符的作用类似于 buffer，所不同的是 window 操作符是把当前流中的元素收集到另外的 Flux 序列中，因此返回值类型是 Flux<Flux<T>>
     */
    public static void window() {
        Flux.range(1, 100).window(20).subscribe(System.out::println);
    }

}
