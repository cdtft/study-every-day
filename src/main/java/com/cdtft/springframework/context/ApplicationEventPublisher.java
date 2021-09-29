package com.cdtft.springframework.context;

/**
 * @author: wangcheng
 * @date: 2021年09月28 15:56
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

}
