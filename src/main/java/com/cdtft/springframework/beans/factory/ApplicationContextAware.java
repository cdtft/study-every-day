package com.cdtft.springframework.beans.factory;

import com.cdtft.springframework.context.ApplicationContext;

/**
 * @author: wangcheng
 * @date: 2021年09月15 15:42
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext);

}
