package com.cdtft.design.listner;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @author: wangcheng
 * @date: 2021年09月26 14:44
 */
@FunctionalInterface
public interface SedEventListener<E extends EventObject> extends EventListener {

    void onEvent(E e);

}
