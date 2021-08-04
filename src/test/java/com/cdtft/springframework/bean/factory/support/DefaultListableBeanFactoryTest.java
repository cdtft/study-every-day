package com.cdtft.springframework.bean.factory.support;

import com.cdtft.springframework.bean.factory.config.BeanDefinition;
import org.junit.Test;

/**
 * @author: wangcheng
 * @date: 2021年08月04 17:18
 */
public class DefaultListableBeanFactoryTest {

    @Test
    public void registerBeanDefinition() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");
        userService.sayHi();
    }
}