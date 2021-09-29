package com.cdtft.springframework.context.event;

import com.cdtft.springframework.context.ApplicationEvent;

/**
 * @author: wangcheng
 * @date: 2021年09月26 16:47
 */
public class ContextClosedEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }

}
