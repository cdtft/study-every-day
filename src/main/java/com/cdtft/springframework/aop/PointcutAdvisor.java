package com.cdtft.springframework.aop;

/**
 * @author: wangcheng
 * @date: 2021年10月12 14:07
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
