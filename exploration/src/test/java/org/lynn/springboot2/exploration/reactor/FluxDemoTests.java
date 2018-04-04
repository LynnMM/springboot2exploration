package org.lynn.springboot2.exploration.reactor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FluxDemoTests {
    @Test
    public void testJust() {
        FluxDemo.just();
    }

    @Test
    public void testFromArray() {
        FluxDemo.fromArray();
    }

    @Test
    public void testEmpty() {
        FluxDemo.empty();
    }

    @Test
    public void testRang() {
        FluxDemo.range(1, 10);
    }

    @Test
    public void testInterval() {
        FluxDemo.interval();
    }

    @Test
    public void testGenerateHello() {
        FluxDemo.generateHello();
    }

    @Test
    public void testGenerateStateBased() {
        FluxDemo.generateStateBased();
    }

    @Test
    public void testGenerateWithMutable() {
        FluxDemo.generateWithMutable();
    }

    @Test
    public void testGenerateWithMutableAndCustomer() {
        FluxDemo.generateWithMutableAndCustomer();
    }

    @Test
    public void testGenerateList() {
        FluxDemo.generateList();
    }

    @Test
    public void testCreate() {
        FluxDemo.create();
    }

    @Test
    public void testDealRangeAndError() {
        FluxDemo.dealRangeAndError();
    }

    @Test
    public void testDealRangeAndErrorCompletion() {
        FluxDemo.dealRangeAndErrorCompletion();
    }

    @Test
    public void testDealErrorAndCompleteMessage() {
        FluxDemo.dealErrorMessage();
    }

    @Test
    public void testJustWithErrorReturn() {
        FluxDemo.justWithErrorReturn();
    }

    @Test
    public void testJustRetry() {
        FluxDemo.justRetry();
    }

    @Test
    public void testCustomSubscribe() {
        FluxDemo.customSubscribe();
    }
}
