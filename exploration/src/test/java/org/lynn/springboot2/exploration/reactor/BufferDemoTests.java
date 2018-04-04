package org.lynn.springboot2.exploration.reactor;

import java.nio.Buffer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BufferDemoTests {

    @Test
    public void testBuffer() {
        BufferDemo.buffer();
    }

    @Test
    public void testBufferUntil() {
        BufferDemo.bufferUntil();
    }

    @Test
    public void testBufferWhile() {
        BufferDemo.bufferWhile();
    }
}
