package com.cdtft.design.adapter;

/**
 * @author : wangcheng
 * @date : 2020年03月14日 11:44
 */
public class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
