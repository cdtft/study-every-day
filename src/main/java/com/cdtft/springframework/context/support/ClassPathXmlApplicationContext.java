package com.cdtft.springframework.context.support;

import com.cdtft.springframework.beans.BeansException;

import java.util.Map;

/**
 * @author: wangcheng
 * @date: 2021年09月02 17:57
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    @Override
    protected String getConfigLocation() {
        return null;
    }

}
