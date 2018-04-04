package org.lynn.springboot2.exploration.reactor;

import java.util.List;

public interface MyEventListener<T> {
    void onDataChunk(List<T> chunk);

    void processComplete();

}
