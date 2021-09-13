package com.cdtft.springframework.beans.factory;

/**
 * @author: wangcheng
 * @date: 2021年09月03 17:25
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;

}
