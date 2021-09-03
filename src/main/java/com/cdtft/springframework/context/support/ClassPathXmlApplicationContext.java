package com.cdtft.springframework.context.support;

/**
 * @author: wangcheng
 * @date: 2021年09月02 17:57
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    @Override
    protected String getConfigLocation() {
        return "classpath:spring.xml";
    }

}
