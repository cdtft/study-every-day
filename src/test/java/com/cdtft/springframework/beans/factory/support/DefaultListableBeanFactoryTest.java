package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.factory.config.BeanDefinition;
import org.junit.Test;

import java.lang.reflect.Constructor;

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

    @Test
    public void getBeanWithConstructor() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService", "王城");
        userService.printName();
    }

    @Test
    public void testConstructor() {
        Class<UserService> clazz = UserService.class;
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            System.out.println(ctor);
        }
    }
}