package com.cdtft.design.listner;

import java.util.EventObject;

/**
 * @author: wangcheng
 * @date: 2021年09月26 14:44
 */
public class SedEventObject extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SedEventObject(Object source) {
        super(source);
    }

}
