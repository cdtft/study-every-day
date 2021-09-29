package com.cdtft.springframework.context.event;

import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.BeanFactory;
import com.cdtft.springframework.beans.factory.BeanFactoryAware;
import com.cdtft.springframework.context.ApplicationEvent;
import com.cdtft.springframework.context.ApplicationListener;
import com.cdtft.springframework.core.io.ClassUtils;
import com.google.common.collect.Lists;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: wangcheng
 * @date: 2021年09月26 17:58
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    private BeanFactory beanFactory;

    private final Set<ApplicationListener<ApplicationEvent>> listenerSet = new HashSet<>();


    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        listenerSet.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        listenerSet.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 匹配事件监听，通过事件对象找到对应的listener
     *
     * @param applicationEvent
     * @return
     */
    protected List<ApplicationListener<ApplicationEvent>> getApplicationListener(ApplicationEvent applicationEvent) {
        List<ApplicationListener<ApplicationEvent>> matchedListener = Lists.newArrayList();
        for (ApplicationListener<ApplicationEvent> listener : listenerSet) {
            if (supportEvent(listener, applicationEvent)) {
                matchedListener.add(listener);
            }
        }
        return matchedListener;
    }


    protected boolean supportEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();
        //判断是否是cglib代理类，如果是取到代理类的父类对象
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        //获取目标类直接实现的接口
        Type genericInterfaces = targetClass.getGenericInterfaces()[0];

        //获取实现接口的具体泛型
        Type actualTypeArgument = ((ParameterizedType) genericInterfaces).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();

        Class<?> subscriptionEventClass = null;
        try {
            subscriptionEventClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("event class name not found:" + className);
        }
        return subscriptionEventClass.isAssignableFrom(event.getClass());
    }

}
