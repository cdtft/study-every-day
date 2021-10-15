package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.BeanReference;
import com.cdtft.springframework.beans.PropertyValue;
import com.cdtft.springframework.beans.PropertyValues;
import com.cdtft.springframework.beans.factory.config.BeanDefinition;
import com.cdtft.springframework.context.support.ClassPathXmlApplicationContext;
import com.cdtft.springframework.core.io.ClassPathResource;
import com.cdtft.springframework.core.io.XmlBeanDefinitionReader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");
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

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.printUid();
    }

    @Test
    public void testXmlBeanDefinitionReader() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.printName();
    }

    @Test
    public void testClassPathResource() {
        ClassPathResource resourceLoader = new ClassPathResource("spring.xml");
        try {
            InputStream inputStream = resourceLoader.getInputStream();
            if (inputStream == null) {
                System.out.println("inputStream is null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testConvertBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        UserDao userDao = beanFactory.getBean("userDao", UserDao.class);
        System.out.println(userDao.findByUserNameById(2));
    }

    @Test
    public void testBeanPostProcessor() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.refresh();
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.printName();
    }


    @Test
    public void testProxyUserDao() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        IUserService iUserService = context.getBean("iUserService", IUserServiceImpl.class);
        System.out.println(iUserService.queryUserInfo());

    }
}