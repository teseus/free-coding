package com.teseus.kata.topcode;

import org.junit.Assert;

public class KiwiJuiceEasy {

    public int[] thePouring(int[] capacities, int[] bottles, int[] fromId, int[] toId){

        for (int i = 0; i < fromId.length; i++) {
            int realCapacity = capacities[toId[i]] - bottles[toId[i]];
            int realAmount = Math.min(realCapacity, bottles[fromId[i]]);
            bottles[fromId[i]] -= realAmount;
            bottles[toId[i]] += realAmount;
        }

        return bottles;
    }

    public static void main(String[] args) {
        case1();
        case2();
        case3();
        case4();
        case5();
    }

    private static void case1() {
        int[] capacities = {20, 20};
        int[] bottles = {5, 8};
        int[] fromId = {0};
        int[] toId = {1};
        int[] results = new KiwiJuiceEasy().thePouring(capacities, bottles, fromId, toId);
        Assert.assertArrayEquals(results, new int[]{0,13});
    }

    private static void case2() {
        int[] capacities = {10, 10};
        int[] bottles = {5, 8};
        int[] fromId = {0};
        int[] toId = {1};
        int[] results = new KiwiJuiceEasy().thePouring(capacities, bottles, fromId, toId);
        Assert.assertArrayEquals(results, new int[]{3,10});
    }

    private static void case3() {
        int[] capacities = {30, 20, 10};
        int[] bottles = {10, 5, 5};
        int[] fromId = {0, 1, 2};
        int[] toId = {1, 2, 0};
        int[] results = new KiwiJuiceEasy().thePouring(capacities, bottles, fromId, toId);
        Assert.assertArrayEquals(results, new int[]{10, 10, 0});
    }

    private static void case4() {
        int[] capacities = {14, 35, 86, 58, 25, 62};
        int[] bottles = {6, 34, 27, 38, 9, 60};
        int[] fromId = {1,2,4,5,3,3,1,0};
        int[] toId = {0,1,2,4,2,5,3,1};
        int[] results = new KiwiJuiceEasy().thePouring(capacities, bottles, fromId, toId);
        Assert.assertArrayEquals(results, new int[]{0, 14, 65, 35, 25, 35});
    }

    private static void case5() {
        int[] capacities = {700000, 800000, 900000, 1000000};
        int[] bottles = {478478, 478478, 478478, 478478};
        int[] fromId = {2,3,2,0,1};
        int[] toId = {0,1,1,3,2};
        int[] results = new KiwiJuiceEasy().thePouring(capacities, bottles, fromId, toId);
        Assert.assertArrayEquals(results, new int[]{0, 156956, 900000, 856956});
    }

}
