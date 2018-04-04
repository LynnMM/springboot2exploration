package org.lynn.springboot2.exploration.reactor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ReduceDemoTests {
    @Test
    public void reduce() {
        ReduceDemo.reduce();
    }

    @Test
    public void reduceWith() {
        ReduceDemo.reduceWith();
    }
}
