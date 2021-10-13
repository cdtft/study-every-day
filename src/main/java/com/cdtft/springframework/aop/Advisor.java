package com.cdtft.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author: wangcheng
 * @date: 2021年10月12 14:00
 */
public interface Advisor {

    Advice getAdvice();

}
