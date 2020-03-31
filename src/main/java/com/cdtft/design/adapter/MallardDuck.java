package com.cdtft.design.adapter;

/**
 * @author : wangcheng
 * @date : 2020年03月14日 11:42
 */
public class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}
