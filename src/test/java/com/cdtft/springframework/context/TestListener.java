package com.cdtft.springframework.context;

/**
 * @author: wangcheng
 * @date: 2021年09月27 16:32
 */
public class TestListener implements ApplicationListener<TestEvent> {

    @Override
    public void onApplicationEvent(TestEvent event) {
        System.out.println("接收到");
    }

}
