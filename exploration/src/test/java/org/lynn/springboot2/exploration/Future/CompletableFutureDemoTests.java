package org.lynn.springboot2.exploration.Future;

import static junit.framework.TestCase.assertTrue;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.lynn.springboot2.exploration.future.CompletableFutureDemo;

@RunWith(JUnit4.class)
public class CompletableFutureDemoTests {
    @Test
    public void testExecuteCompletableFuture() {
        List<String> result = CompletableFutureDemo.executeCompletableFuture();
        assertTrue(result != null && result.size() > 0);
    }
}
