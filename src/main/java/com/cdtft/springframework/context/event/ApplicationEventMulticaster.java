package com.cdtft.springframework.context.event;

import com.cdtft.springframework.context.ApplicationEvent;
import com.cdtft.springframework.context.ApplicationListener;

/**
 * @author: wangcheng
 * @date: 2021年09月26 17:03
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加监听
     *
     * @param applicationEvent
     */
    void addApplicationListener(ApplicationListener<?> applicationEvent);

    /**
     * 移除监听
     *
     * @param applicationEvent
     */
    void removeApplicationListener(ApplicationListener<?> applicationEvent);

    /**
     * 广播事件
     *
     * @param applicationEvent
     */
    void multicastEvent(ApplicationEvent applicationEvent);

}
