package com.cdtft.springframework.context.support;

import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.cdtft.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.cdtft.springframework.beans.factory.config.BeanPostProcessor;
import com.cdtft.springframework.beans.factory.support.ApplicationContextAwareProcessor;
import com.cdtft.springframework.context.ApplicationEvent;
import com.cdtft.springframework.context.ApplicationListener;
import com.cdtft.springframework.context.ConfigurableApplicationContext;
import com.cdtft.springframework.context.event.ApplicationEventMulticaster;
import com.cdtft.springframework.context.event.ContextClosedEvent;
import com.cdtft.springframework.context.event.ContextRefreshedEvent;
import com.cdtft.springframework.context.event.SimpleApplicationEventMulticaster;
import com.cdtft.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @author: wangcheng
 * @date: 2021年09月02 14:59
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {

        //创建BeanFactory并加载BeanDefinition
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //提供修改BeanDefinition的机会
        invokeBeanFactoryPostProcessors(beanFactory);

        //组册beanPostProcessors
        registerBeanPostProcessors(beanFactory);

        //初始化事件
        initApplicationEventMulticaster();

        //注册事件监听器
        registerListeners();

        beanFactory.preInstantiateSingletons();

        registerShutdownHook();

        finishRefresh();

    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        return getBeanFactory().getBean(name, clazz);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBeansOfType(requiredType);
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return getBeanFactory().getBean(clazz);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {

        publishEvent(new ContextClosedEvent(this));

        getBeanFactory().destroySingletons();
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton("applicationEventMulticaster", applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> listeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : listeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }
}

