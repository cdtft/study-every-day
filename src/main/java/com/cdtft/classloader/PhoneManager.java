package com.cdtft.classloader;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : wangcheng
 * @date : 2020年04月16日 14:34
 */
public class PhoneManager {

    private static final CopyOnWriteArrayList<Phone> phoneContainer = new CopyOnWriteArrayList();

    public static void registerPhone(Phone phone) {
        phoneContainer.addIfAbsent(phone);
    }

    public static Phone getPhone(int money) {
        for (Phone phone : phoneContainer) {
            if ( null != phone.getPhone(money)) {
                return phone;
            }
        }
        throw new RuntimeException("没有对映的价位的商品");
    }
}
