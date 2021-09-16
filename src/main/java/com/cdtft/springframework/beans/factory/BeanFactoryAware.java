package com.cdtft.springframework.beans.factory;

import com.cdtft.springframework.beans.BeansException;

/**
 * 实现该接口感知到所属的BeanFactory
 *
 * @author: wangcheng
 * @date: 2021年09月15 15:18
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
