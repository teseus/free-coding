package com.teseus.kata.topcode;

import static org.junit.Assert.assertEquals;

public class CryptoGraphy {
    public static void main(String[] args) {
        assertEquals(12, CryptoGraphy.encrypt(new int[]{1,2,3}));
        assertEquals(36, CryptoGraphy.encrypt(new int[]{1,3,2,1,1,3}));
        assertEquals(2, CryptoGraphy.encrypt(new int[]{1,1,1,1}));
        assertEquals(986074810223904000L, CryptoGraphy.encrypt(new int[]{1000,999,998,997,996,995}));
    }

    private static long encrypt(int[] numbers) {
        numbers[getMinIndex(numbers)] += 1;
        long total = 1;
        for (int number : numbers) {
            total *= number;
        }
        return total;
    }

    private static int getMinIndex(int[] numbers) {
        int minIdx = 0;
        for (int i = 1; i < numbers.length; i++) {
            if(numbers[i] < numbers[minIdx])
                minIdx = i;
        }
        return minIdx;
    }
}
