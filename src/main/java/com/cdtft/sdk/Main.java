package com.cdtft.sdk;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int a1 = x % 10;
        int a2 = x / 10 % 10;
        int a3 = x / 10 / 10 % 10;
        int a4 = x / 10 / 10 / 10 % 10;
        int a5 = x / 10 / 10 / 10 / 10 % 10;
        int a6 = x / 10 / 10 / 10 / 10 / 10 % 10;
        int pre = a1 + a2 + a3;
        int suf = a4 + a5 + a6;
        int diff = pre -suf;
        if (diff < 0) {

        }
    }
}
