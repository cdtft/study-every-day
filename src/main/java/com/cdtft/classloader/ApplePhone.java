package com.cdtft.classloader;

/**
 * @author : wangcheng
 * @date : 2020年04月16日 14:54
 */
public class ApplePhone implements Phone {

    static {
        System.out.println("register apple phone to phone manager");
        PhoneManager.registerPhone(new ApplePhone());
    }

    @Override
    public void call() {
        System.out.println("apple phone call");
    }

    @Override
    public void watchTV() {
    }

    @Override
    public Phone getPhone(int money) {
        if (money >= 5000) {
            return this;
        }
        return null;
    }

}
