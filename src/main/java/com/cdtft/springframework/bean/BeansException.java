package com.cdtft.springframework.bean;

/**
 * @author: wangcheng
 * @date: 2021年08月02 16:43
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
