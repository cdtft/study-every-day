package com.cdtft.springframework.aop;

/**
 * @author: wangcheng
 * @date: 2021年10月09 10:20
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return target;
    }

}
