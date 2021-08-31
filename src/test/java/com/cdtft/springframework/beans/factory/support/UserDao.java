package com.cdtft.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangcheng
 * @date: 2021年08月27 11:29
 */
public class UserDao {

    private static final Map<Integer, String> idToUserNameMap = new HashMap<>();

    static {
        idToUserNameMap.put(1, "张三");
        idToUserNameMap.put(2, "李四");
        idToUserNameMap.put(3, "王五");
        idToUserNameMap.put(4, "小艾");
    }

    public String findByUserNameById(Integer userId) {
        return idToUserNameMap.get(userId);
    }
}