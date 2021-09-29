package com.cdtft.springframework.context;

import com.cdtft.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: wangcheng
 * @date: 2021年09月27 16:30
 */
public class ApplicationListenerTest {

    @Test
    public void testSupportEvent() {
        TestListener listener = new TestListener();
        Class<? extends TestListener> aClass = listener.getClass();

        //直接实现interface
        ParameterizedType genericInterfaces = (ParameterizedType) aClass.getGenericInterfaces()[0];

        //获取泛型类型
        Type actualTypeArgument = genericInterfaces.getActualTypeArguments()[0];

        System.out.println(actualTypeArgument.getTypeName());

        String className = actualTypeArgument.getTypeName();
        Class<?> subscriptionEventClass = null;
        try {
            subscriptionEventClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ApplicationEvent event = new TestEvent(new User());
        System.out.println(event.getClass());

        System.out.println(subscriptionEventClass.isAssignableFrom(event.getClass()));
        System.out.println(subscriptionEventClass.isAssignableFrom(subscriptionEventClass));
    }

    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.publishEvent(new TestEvent(new User()));
    }

}