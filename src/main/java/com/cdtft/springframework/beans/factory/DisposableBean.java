package com.cdtft.springframework.beans.factory;

/**
 * @author: wangcheng
 * @date: 2021年09月03 17:30
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
