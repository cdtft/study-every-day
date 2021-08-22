package com.cdtft.springframework.beans;

/**
 * @author: wangcheng
 * @date: 2021年08月20 14:25
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
