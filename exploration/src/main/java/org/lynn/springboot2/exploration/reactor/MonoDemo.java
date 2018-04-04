package org.lynn.springboot2.exploration.reactor;

import java.util.Optional;
import reactor.core.publisher.Mono;

public class MonoDemo {
    /**
     * Mono 类中也包含了一些与 Flux 类中相同的静态方法。这些方法包括 just()，empty()，error()和 never()等。除了这些方法之外，Mono 还有一些独有的静态方法。
     */

    /**
     * fromCallable()、fromCompletionStage()、fromFuture()、fromRunnable()和 fromSupplier()：分别从
     * Callable、CompletionStage、CompletableFuture、Runnable 和 Supplier 中创建 Mono。
     */
    public static void fromSupplier() {
        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
    }

    /**
     * justOrEmpty(Optional<? extends T> data)和 justOrEmpty(T data)：从一个 Optional 对象或可能为 null 的对象中创建 Mono。只有 Optional
     * 对象中包含值或对象不为 null 时，Mono 序列才产生对应的元素。
     */
    public static void justOrEmpty() {
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
    }

    /**
     * 通过 create()方法来使用 MonoSink 来创建 Mono
     */
    public static void create() {
        Mono.create(monoSink -> monoSink.success("Hello")).subscribe(System.out::println);
    }
}
