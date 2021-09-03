package com.cdtft.springframework.beans.factory;

import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 *提供修改bean definition的能力
 *
 * @author: wangcheng
 * @date: 2021年09月02 13:50
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory {

    void preInstantiateSingletons() throws BeansException;

}
