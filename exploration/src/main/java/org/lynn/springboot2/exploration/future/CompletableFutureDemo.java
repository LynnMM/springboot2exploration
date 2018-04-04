package org.lynn.springboot2.exploration.future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureDemo {
    /**
     * We get a list of IDs from which we want to fetch a name and a statistic and combine these pair-wise, all of it
     * asynchronously.
     */

    /**
     * 使用CompletableFuture执行异步调用链
     */
    public static List<String> executeCompletableFuture() {
        CompletableFuture<List<String>> ids = ifhIds();

        CompletableFuture<List<String>> result = ids.thenComposeAsync(l -> {
            Stream<CompletableFuture<String>> zip = l.stream().map(i -> {
                CompletableFuture<String> nameTask = ifhName(i);
                CompletableFuture<Integer> statTask = ifhStat(i);

                return nameTask.thenCombineAsync(statTask, (name, stat) -> "Name " + name + "has stats " + stat);
            });

            List<CompletableFuture<String>> combinationList = zip.collect(Collectors.toList());
            CompletableFuture<String>[] combinationArray
                = combinationList.toArray(new CompletableFuture[combinationList.size()]);
            CompletableFuture<Void> allDone = CompletableFuture.allOf(combinationArray);

            return allDone
                .thenApply(v -> combinationList.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        });

        List<String> results = result.join();
        return results;
    }

    private static CompletableFuture<List<String>> ifhIds() {
        CompletableFuture<List<String>> result = new CompletableFuture<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> ids = Arrays.asList("0", "1", "2", "3", "4");
                result.complete(ids);
            }
        }).start();
        return result;
    }

    private static CompletableFuture<String> ifhName(String id) {
        CompletableFuture<String> result = new CompletableFuture<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = null;
                switch (id) {
                    case "0":
                        name = "Joe";
                        break;
                    case "1":
                        name = "Bart";
                        break;
                    case "2":
                        name = "Henry";
                        break;
                    case "3":
                        name = "Nicole";
                        break;
                    case "4":
                        name = "ABSLAJNFOAJNFOANFANSF";
                        break;
                    default:
                        name = "name";
                }

                result.complete(name);
            }
        }).start();

        return result;
    }

    private static CompletableFuture<Integer> ifhStat(String id) {
        CompletableFuture<Integer> result = new CompletableFuture<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int stat = 0;
                switch (id) {
                    case "0":
                        stat = 103;
                        break;
                    case "1":
                        stat = 104;
                        break;
                    case "2":
                        stat = 105;
                        break;
                    case "3":
                        stat = 106;
                        break;
                    case "4":
                        stat = 121;
                        break;
                    default:
                        stat = 0;
                }

                result.complete(stat);
            }
        }).start();

        return result;
    }
}
