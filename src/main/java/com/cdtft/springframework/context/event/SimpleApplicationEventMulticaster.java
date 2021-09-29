package com.cdtft.springframework.context.event;

import com.cdtft.springframework.beans.factory.BeanFactory;
import com.cdtft.springframework.context.ApplicationEvent;
import com.cdtft.springframework.context.ApplicationListener;

import java.util.List;

/**
 * @author: wangcheng
 * @date: 2021年09月28 16:08
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent applicationEvent) {
        List<ApplicationListener<ApplicationEvent>> applicationListeners = getApplicationListener(applicationEvent);
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            listener.onApplicationEvent(applicationEvent);
        }
    }
}
