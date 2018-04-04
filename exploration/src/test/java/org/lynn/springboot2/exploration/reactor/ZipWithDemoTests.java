package org.lynn.springboot2.exploration.reactor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ZipWithDemoTests {
    @Test
    public void testZipWith() {
        ZipWithDemo.zipWith();
    }

    @Test
    public void testZipWithCombined() {
        ZipWithDemo.zipWithCombined();
    }
}
