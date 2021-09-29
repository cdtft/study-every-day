package com.cdtft.springframework.context;

import java.util.EventListener;

/**
 * @author: wangcheng
 * @date: 2021年09月26 17:11
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);

}
