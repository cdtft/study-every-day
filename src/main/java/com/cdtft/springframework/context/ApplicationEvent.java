package com.cdtft.springframework.context;

import java.util.EventObject;

/**
 * @author: wangcheng
 * @date: 2021年09月26 16:07
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }

}
