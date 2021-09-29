package com.cdtft.design.listner;

import org.junit.Test;

/**
 * @author: wangcheng
 * @date: 2021年09月26 14:54
 */
public class SedEventPublisherTest {

    @Test
    public void publish() {
        SedEventPublisher<SedEventObject> publisher = new SedEventPublisher<>(sedEventObject -> {
            System.out.println("接受到事件");
            TestEventSource source = (TestEventSource) sedEventObject.getSource();
            System.out.println(source.getName());
        });
        TestEventSource source = new TestEventSource();
        source.setName("test");

        publisher.publish(new SedEventObject(source));
    }
}