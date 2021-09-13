package com.cdtft.springframework.context;

import com.cdtft.springframework.beans.BeansException;

/**
 * @author: wangcheng
 * @date: 2021年09月02 14:45
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器，加载 -> 修改 -> 注册 -> 实例化
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
