package com.cdtft.java8;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年03月31日 15:35
 */
public class Main {

    public static void main(String[] args) {
        List<Item> itemList = Lists.newArrayList();
        itemList.add(new Item(1));
        itemList.add(new Item(2));
        itemList.add(new Item(3));
        itemList.add(new Item(4));
        itemList.add(new Item(5));
        Integer result = itemList.stream()
                .mapToInt(o -> o.getTotal())
                .sum();
        System.out.println(result);
    }
}
