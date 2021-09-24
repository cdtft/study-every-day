package com.cdtft.springframework.beans.factory;

/**
 * @author: wangcheng
 * @date: 2021年09月23 15:00
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
