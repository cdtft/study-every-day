package com.cdtft.classloader;

/**
 * @author : wangcheng
 * @date : 2020年04月16日 14:53
 */
public class AndroidPhone implements Phone {

    static {
        System.out.println("android phone register to phone manager");
        PhoneManager.registerPhone(new AndroidPhone());
    }

    @Override
    public void call() {
        System.out.println("android phone call");
    }

    @Override
    public void watchTV() {

    }

    @Override
    public Phone getPhone(int money) {
        if (money < 5000) {
            return this;
        }
        return null;
    }
}
