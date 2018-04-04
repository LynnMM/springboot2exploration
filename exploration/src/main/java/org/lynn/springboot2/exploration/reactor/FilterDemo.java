package org.lynn.springboot2.exploration.reactor;

import reactor.core.publisher.Flux;

public class FilterDemo {

    /**
     * 对流中包含的元素进行过滤，只留下满足 Predicate 指定条件的元素。
     */
    public static void filter() {
        Flux.range(1, 10).filter(integer -> integer % 2 == 0).subscribe(System.out::println);
    }

}
