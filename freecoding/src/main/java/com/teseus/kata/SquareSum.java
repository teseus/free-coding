package com.teseus.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareSum {
    public static int squareSum(int[] p) {
        return Arrays.stream(p)
                .reduce(0, (a, b) -> a + b * b);
    }

    @Test
    public void testBasic()
    {
        //then
        assertEquals(9, SquareSum.squareSum(new int[] {1,2,2}));
        assertEquals(5, SquareSum.squareSum(new int[] {1,2}));
        assertEquals(50, SquareSum.squareSum(new int[] {5,-3,4}));
    }
}
