package com.cdtft.function.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * @author: wangcheng
 * @date: 2022年02月07 11:15
 */
public class ReduceTest {

    @Test
    public void count() {
        int count = Stream.of(1, 2, 3)
                //acc 累加器，保存当前累加的结果
                //element 当前stream中的元素
                .reduce(0, (acc, element) -> acc + element);
        Assert.assertEquals(count, 6);
    }

    @Test
    public void openReduce() {
        BinaryOperator<Integer> accumulator
                = (acc, element) -> acc + element;
        int count = accumulator.apply(accumulator.
                apply(accumulator.apply(0, 1), 2), 3);
        Assert.assertEquals(count, 6);
    }

    @Test
    public void concatName() {
        StringBuilder reduce = Stream.of("wang", "cheng", "li").reduce(new StringBuilder(), (builder, name) -> {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(name);
            return builder;
        }, StringBuilder::append);
        reduce.insert(0, "[");
        reduce.append("]");
        System.out.println(reduce.toString());
    }

    @Test
    public void testStringCombiner() {
        StringCombiner reduce = Stream.of("wang", "cheng", "li").reduce(new StringCombiner(), StringCombiner::add, StringCombiner::merge);
        System.out.println(reduce.builder.toString());
    }
}
