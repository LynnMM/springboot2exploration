package org.lynn.springboot2.exploration.reactor;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FluxDemo {

    /**
     * just()：可以指定序列中包含的全部元素。创建出来的 Flux 序列在发布这些元素之后会自动结束。
     */
    public static void just() {
        Flux.just("Hello", "World").subscribe(System.out::println);
    }

    /**
     * fromArray()，fromIterable()和 fromStream()：可以从一个数组、Iterable 对象或 Stream 对象中创建 Flux 对象。
     */
    public static void fromArray() {
        Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
    }

    /**
     * empty()：创建一个不包含任何元素，只发布结束消息的序列。
     */
    public static void empty() {
        Flux.empty().subscribe(System.out::println);
    }

    /**
     * range(int start, int count)：创建包含从 start 起始的 count 个数量的 Integer 对象的序列。
     */
    public static void range(int start, int count) {
        Flux.range(start, count).subscribe(System.out::println);
    }

    /**
     * interval(Duration period)和 interval(Duration delay, Duration period)： 创建一个包含了从 0 开始递增的 Long
     * 对象的序列。其中包含的元素按照指定的间隔来发布。 除了间隔时间之外，还可以指定起始元素发布之前的延迟时间。
     */
    public static void interval() {
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
    }

    /**
     * intervalMillis(long period)和 intervalMillis(long delay, long period)： 与
     * interval()方法的作用相同，只不过该方法通过毫秒数来指定时间间隔和延迟时间。
     */
    public static void intervalMillis() {
        Flux.interval(Duration.ofMillis(1000)).subscribe(System.out::println);
    }

    /**
     * generate()方法通过同步和逐一的方式来产生 Flux 序列。 序列的产生是通过调用所提供的 SynchronousSink 对象的 next()，complete()和 error(Throwable)方法来完成的。
     */

    /**
     * 通过 next()方法产生一个简单的值，然后通过 complete()方法来结束该序列
     */
    public static void generateHello() {
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);
    }

    public static void generateStateBased() {
        Flux<String> flux = Flux.generate(() -> 0, (state, sink) -> {
            sink.next("3 x " + state + " = " + 3 * state);
            if (state == 10) {
                sink.complete();
            }
            return state + 1;
        });
        flux.subscribe(System.out::println);
    }

    public static void generateWithMutable() {
        Flux<String> flux = Flux.generate(AtomicLong::new, (state, sink) -> {
            long i = state.getAndIncrement();
            sink.next("3 x " + i + " = " + 3 * i);
            if (i == 10) {
                sink.complete();
            }
            return state;
        });
        flux.subscribe(System.out::println);
    }

    public static void generateWithMutableAndCustomer() {
        Flux<String> flux = Flux.generate(AtomicLong::new, (state, sink) -> {
            long i = state.getAndIncrement();
            sink.next("3 x " + i + " = " + 3 * i);
            if (i == 10) {
                sink.complete();
            }
            return state;
        }, (state) -> System.out.println("state: " + state));
        flux.subscribe(System.out::println);
    }

    /**
     * 状态对象是一个 ArrayList 对象。 实际产生的值是一个随机数。 产生的随机数被添加到 ArrayList 中。当产生了 10 个数时，通过 complete()方法来结束序列。
     */
    public static void generateList() {
        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }

    /**
     * create()方法与 generate()方法的不同之处在于所使用的是 FluxSink 对象。FluxSink 支持同步和异步的消息产生，并且可以在一次调用中产生多个元素。在代码清单 3 中，在一次调用中就产生了全部的
     * 10 个元素。 create can be very useful to bridge an existing API with the reactive world - such as an asynchronous API
     * based on listeners
     */
    public static void create() {
        Flux.create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        }).subscribe(System.out::println);
    }

    /**
     * create can be very useful to bridge an existing API with the reactive world - such as an asynchronous API based
     * on listeners
     */
    public static void createUseLIstenerBasedAPI() {
        /*Flux<String> bridge = Flux.create(sink -> {
            myEventProcessor.register(new MyEventListener<String>() {
        
                @Override
                public void onDataChunk(List<String> chunk) {
                    for (String s : chunk) {
                        sink.next(s);
                    }
                }
        
                @Override
                public void processComplete() {
                    sink.complete();
                }
            });
        });*/
    }

    /**
     * The subscribe method has two lambda expressions: one for the content we expect and one for errors.
     */
    public static void dealRangeAndError() {
        Flux<Integer> ints = Flux.range(1, 4).map(integer -> {
            if (integer < 4) {
                return integer;
            }
            throw new RuntimeException("Got to 4");
        });
        ints.subscribe(integer -> System.out.println(integer), error -> System.err.println("Error: " + error));
    }

    /**
     * The subscribe method includes both an error handler and a handler for completion events
     */
    public static void dealRangeAndErrorCompletion() {
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i), error -> System.err.println("Error " + error), () -> {
            System.out.println("Done");
        });
    }

    /**
     * 在调用 subscribe 方法时可以指定需要处理的消息类型。可以只处理其中包含的正常消息，也可以同时处理错误消息和完成消息。 代码清单 15 中通过 subscribe()方法同时处理了正常消息和错误消息。
     */
    public static void dealErrorMessage() {
        Flux.just(1, 2).concatWith(Mono.error(new IllegalStateException())).subscribe(System.out::println,
            System.err::println);
    }

    /**
     * 当出现错误时，有多种不同的处理策略。第一种策略是通过 onErrorReturn()方法返回一个默认值
     */
    public static void justWithErrorReturn() {
        Flux.just(1, 2).concatWith(Mono.error(new IllegalStateException())).onErrorReturn(0)
            .subscribe(System.out::println);
    }

    /**
     * 第二种策略是通过 switchOnError()方法来使用另外的流来产生元素
     */
    public static void justSwitchOnError() {
        /*Flux.just(1, 2).concatWith(Mono.error(new IllegalStateException())).switchOnError(Mono.just(0))
            .subscribe(System.out::println);*/
    }

    /**
     * 第三种策略是通过 onErrorResumeWith()方法来根据不同的异常类型来选择要使用的产生元素的流
     */
    public static void justOnErrorResumeWith() {
        /*Flux.just(1, 2).concatWith(Mono.error(new IllegalArgumentException())).onErrorResumeWith(e -> {
            if (e instanceof IllegalStateException) {
                return Mono.just(0);
            } else if (e instanceof IllegalArgumentException) {
                return Mono.just(-1);
            }
            return Mono.empty();
        }).subscribe(System.out::println);*/
    }

    /**
     * 当出现错误时，还可以通过 retry 操作符来进行重试
     */
    public static void justRetry() {
        Flux.just(1, 2).concatWith(Mono.error(new IllegalStateException())).retry(1).subscribe(System.out::println);
    }

    public static void createSchedulers() {
        Flux.create(fluxSink -> {
            fluxSink.next(Thread.currentThread().getName());
            fluxSink.complete();
        }).publishOn(Schedulers.single()).map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
            .publishOn(Schedulers.elastic()).map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
            .subscribeOn(Schedulers.parallel()).toStream().forEach(System.out::println);
    }

    public static void customSubscribe() {
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i), error -> System.err.println("Error " + error), () -> {
            System.out.println("Done");
        }, s -> ss.request(10));
        ints.subscribe(ss);
    }
}
