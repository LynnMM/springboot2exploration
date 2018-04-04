package org.lynn.springboot2.exploration.reactor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MonoDemoTests {

    @Test
    public void testFromSupplier() {
        MonoDemo.fromSupplier();
    }

    @Test
    public void testJustOrEmpty() {
        MonoDemo.justOrEmpty();
    }

    @Test
    public void create() {
        MonoDemo.create();
    }
}
