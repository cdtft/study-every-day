package com.cdtft.springframework.context.support;

import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.cdtft.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.cdtft.springframework.beans.factory.config.BeanPostProcessor;
import com.cdtft.springframework.context.ConfigurableApplicationContext;
import com.cdtft.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author: wangcheng
 * @date: 2021年09月02 14:59
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {

        //创建BeanFactory并加载BeanDefinition
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        invokeBeanFactoryPostProcessors(beanFactory);

        //组册beanPostProcessors
        registerBeanPostProcessors(beanFactory);

        beanFactory.preInstantiateSingletons();

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
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
}

