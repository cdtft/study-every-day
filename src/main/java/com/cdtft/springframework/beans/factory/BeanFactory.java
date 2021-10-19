package com.cdtft.springframework.beans.factory;

/**
 * @author: wangcheng
 * @date: 2021年08月02 16:43
 */
public interface BeanFactory {

    Object getBean(String name);

    Object getBean(String name, Object... args);

    <T> T getBean(String name, Class<T> clazz);

    <T> T getBean(Class<T> clazz);

}
