package com.cdtft.sdk.proxy.framework;

import java.util.HashMap;

/**
 * @author : wangcheng
 * @date : 2020年04月28日 16:20
 */
public class ApplicationContext implements BeanFactory {

    protected final static HashMap<String, Object> container = new HashMap<>();

    @Override
    public Object getBean(String name) {
        return null;
    }
}
