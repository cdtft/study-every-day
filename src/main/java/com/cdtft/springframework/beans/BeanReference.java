package com.cdtft.springframework.beans;

/**
 * bean引用类
 *
 * @author: wangcheng
 * @date: 2021年08月27 11:12
 */
public class BeanReference {

    private final String name;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
