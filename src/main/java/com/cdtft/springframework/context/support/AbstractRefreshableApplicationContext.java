package com.cdtft.springframework.context.support;

import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author: wangcheng
 * @date: 2021年09月02 17:11
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory defaultListableBeanFactory;

    public AbstractRefreshableApplicationContext() {
        refresh();
    }

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.defaultListableBeanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected DefaultListableBeanFactory getBeanFactory() {
        return defaultListableBeanFactory;
    }

}
