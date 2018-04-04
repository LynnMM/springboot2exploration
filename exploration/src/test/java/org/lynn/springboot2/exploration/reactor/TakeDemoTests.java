package org.lynn.springboot2.exploration.reactor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TakeDemoTests {
    @Test
    public void testTake() {
        TakeDemo.take();
    }

    @Test
    public void testTakeLast() {
        TakeDemo.takeLast();
    }

    @Test
    public void testTakeWhile() {
        TakeDemo.takeWhile();
    }

    @Test
    public void testTakeUnitl() {
        TakeDemo.takeUntil();
    }
}
