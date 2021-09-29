package com.cdtft.design.listner;

import java.util.EventObject;

/**
 * @author: wangcheng
 * @date: 2021年09月26 14:51
 */
public class SedEventPublisher<E extends EventObject> {

    private SedEventListener<E> listener;

    public SedEventPublisher(SedEventListener<E> listener) {
        this.listener = listener;
    }

    public void publish(E e) {
        System.out.println("publish event to listener");
        listener.onEvent(e);
    }

}
