package com.cdtft.design.adapter;

/**
 * @author : wangcheng
 * @date : 2020年03月14日 11:46
 */
public class TurkeyAdapter implements Duck {

    private final Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }

    public static void main(String[] args) {

    }
}
