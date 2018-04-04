package org.lynn.springboot2.exploration.reactor;

import static junit.framework.TestCase.assertTrue;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ReactorDemoTests {
    @Test
    public void testAsync() {
        List<String> result = ReactorDemo.aysnc();
        assertTrue(result != null && result.size() > 0);
    }
}
