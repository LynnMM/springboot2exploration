package org.lynn.springboot2.exploration.reactor;

import reactor.core.publisher.Flux;

public class MergeDemo {
    /**
     * merge 和 mergeSequential 操作符用来把多个流合并成一个 Flux 序列
     */

    /**
     * merge 按照所有流中元素的实际产生顺序来合并
     */
    public static void merge() {
        /*Flux.merge(Flux.intervalMillis(0, 100).take(5), Flux.intervalMillis(50, 100).take(5)).toStream()
            .forEach(System.out::println);*/
    }

    /**
     * mergeSequential 则按照所有流被订阅的顺序，以流为单位进行合并
     */
    public static void mergeSequential() {
        /*        Flux.mergeSequential(Flux.intervalMillis(0, 100).take(5), Flux.intervalMillis(50, 100).take(5)).toStream()
            .forEach(System.out::println);*/
    }
}
