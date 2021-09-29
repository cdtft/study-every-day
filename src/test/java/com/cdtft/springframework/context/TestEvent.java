package com.cdtft.springframework.context;

/**
 * @author: wangcheng
 * @date: 2021年09月27 16:32
 */
public class TestEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TestEvent(User source) {
        super(source);
    }

}
