package org.lynn.springboot2.exploration.reactor;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactorDemo {

    public static List<String> aysnc() {
        Flux<String> ids = ifhrIds();
        Flux<String> combinations = ids.flatMap(id -> {
            Mono<String> nameTask = ifhrName(id);
            Mono<Integer> statTask = ifhrStat(id);

            return nameTask.zipWith(statTask, (name, stat) -> "Name " + name + " has stats " + stat);
        });

        Mono<List<String>> result = combinations.collectList();
        // Most probably, we would return the result Mono.
        // Since we are in a test, we block, waiting for the processing to finish instead,
        // and then directly return the aggregated list of values.
        return result.block();
    }

    private static Flux<String> ifhrIds() {
        return Flux.just("0", "1", "2", "3", "4");
    }

    private static Mono<String> ifhrName(String id) {
        return Mono.fromSupplier(() -> {
            switch (id) {
                case "0":
                    return "Joe";
                case "1":
                    return "Bart";
                case "2":
                    return "Henry";
                case "3":
                    return "Nicole";
                case "4":
                    return "ABSLAJNFOAJNFOANFANSF";
                default:
                    return "Name";
            }
        });
    }

    private static Mono<Integer> ifhrStat(String id) {
        return Mono.fromSupplier(() -> {
            switch (id) {
                case "0":
                    return 103;
                case "1":
                    return 104;
                case "2":
                    return 105;
                case "3":
                    return 106;
                case "4":
                    return 121;
                default:
                    return 0;
            }
        });
    }
}
