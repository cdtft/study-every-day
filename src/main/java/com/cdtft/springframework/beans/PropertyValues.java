package com.cdtft.springframework.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wangcheng
 * @date: 2021年08月20 14:33
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    private final Map<String, PropertyValue> namePropertyValueMap = new HashMap<>();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
        this.namePropertyValueMap.put(pv.getFieldName(), pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        return namePropertyValueMap.get(propertyName);
    }

}
