package com.cdtft.springframework.beans;

/**
 * @author: wangcheng
 * @date: 2021年08月20 14:25
 */
public class PropertyValue {

    private final String fieldName;

    private final Object value;

    public PropertyValue(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }
}
