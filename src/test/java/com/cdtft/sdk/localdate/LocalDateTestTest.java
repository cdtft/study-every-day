package com.cdtft.sdk.localdate;

import org.junit.Test;

import java.time.LocalDate;

public class LocalDateTestTest {

    @Test
    public void instanceLocalDateTest() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
    }
}