package com.teseus.kata.topcode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class InterestingDigits {
    public static final int TEST_NUM = 118;

    public static void main(String[] args) {
        assertArrayEquals(new Integer[]{3,9}, InterestingDigits.digits(10).toArray());
        assertArrayEquals(new Integer[]{2}, InterestingDigits.digits(3).toArray());
        assertArrayEquals(new Integer[]{2,4,8}, InterestingDigits.digits(9).toArray());
//        assertArrayEquals(new Integer[]{29}, InterestingDigits.digits(30).toArray());
    }

    private static List<Integer> digits(int base) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 2; i < base; i++) {
            if(isOk(base, i))
                nums.add(i);
        }
        return nums;
    }

    private static boolean isOk(int base, int target) {
        long test = TEST_NUM * target;
        long sum = 0;
        while(test > base){
            sum += test % base;
            test /= base;
        }
        sum += test;
        return (sum/(target * 1.0)) - (int)(sum/(target * 1.0)) == 0.0;
    }
}
