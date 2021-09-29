package com.cdtft.springframework.context.event;

import com.cdtft.springframework.context.ApplicationContext;
import com.cdtft.springframework.context.ApplicationEvent;

/**
 * @author: wangcheng
 * @date: 2021年09月26 16:17
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
