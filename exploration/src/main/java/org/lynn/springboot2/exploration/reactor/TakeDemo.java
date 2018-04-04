package org.lynn.springboot2.exploration.reactor;

import reactor.core.publisher.Flux;

public class TakeDemo {
    /**
     * take 系列操作符用来从当前流中提取元素
     */

    /**
     * take(long n)，take(Duration timespan)和 takeMillis(long timespan)：按照指定的数量或时间间隔来提取。
     */
    public static void take() {
        Flux.range(1, 1000).take(10).subscribe(System.out::println);
    }

    /**
     * takeLast(long n)：提取流中的最后 N 个元素
     */
    public static void takeLast() {
        Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);
    }

    /**
     * takeWhile(Predicate<? super T> continuePredicate)： 当 Predicate 返回 true 时才进行提取
     */
    public static void takeWhile() {
        Flux.range(1, 1000).takeWhile(integer -> integer < 10).subscribe(System.out::println);
    }

    /**
     * takeUntil(Predicate<? super T> predicate)：提取元素直到 Predicate 返回 true
     */
    public static void takeUntil() {
        Flux.range(1, 1000).takeUntil(integer -> integer == 10).subscribe(System.out::println);
    }
}
