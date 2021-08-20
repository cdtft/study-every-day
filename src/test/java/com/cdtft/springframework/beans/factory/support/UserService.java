package com.cdtft.springframework.beans.factory.support;

/**
 * @author: wangcheng
 * @date: 2021年08月04 17:23
 */
public class UserService {

    private final String name;

    public UserService(String name) {
        this.name = name;
    }

    public void sayHi() {
        System.out.println("hi!");
    }

    public void printName() {
        System.out.println(name);
    }
}
