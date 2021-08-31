package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.BeanReference;
import com.cdtft.springframework.beans.PropertyValue;
import com.cdtft.springframework.beans.PropertyValues;
import com.cdtft.springframework.beans.factory.config.BeanDefinition;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author: wangcheng
 * @date: 2021年08月04 17:18
 */
public class DefaultListableBeanFactoryTest {

    /**
     * 测试bean的注册
     */
    @Test
    public void registerBeanDefinition() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");
        userService.sayHi();
    }

    /**
     * 使用不同初始化策略创建带构造参数的bean
     */
    @Test
    public void getBeanWithConstructor() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService", "wangcheng");
        userService.printName();
    }

    /**
     * 获取class的构造器信息
     */
    @Test
    public void testConstructor() {
        Class<UserService> clazz = UserService.class;
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            System.out.println(ctor);
        }
    }

    /**
     * 给bean初始化
     */
    @Test
    public void applyPropertyValues() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao", beanDefinition);

        PropertyValues propertyValues = new PropertyValues();
        PropertyValue propertyValue = new PropertyValue("userDao", new BeanReference("userDao"));
        propertyValues.addPropertyValue(propertyValue);

        BeanDefinition userServiceBeanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", userServiceBeanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService", "wangcheng");
        userService.printName(2);
    }

}