package com.cdtft.springframework.bean.factory;

/**
 * @author: wangcheng
 * @date: 2021年08月02 16:43
 */
public interface BeanFactory {

    Object getBean(String name);

    Object getBean(String name, Object... args);

}
