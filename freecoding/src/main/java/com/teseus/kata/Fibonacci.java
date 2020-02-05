package com.teseus.kata;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
//https://www.codewars.com/kata/53d40c1e2f13e331fc000c26/train/java
public class Fibonacci {

    public static BigInteger fib(BigInteger n) {
        if(n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)){
            return n;
        }

        int start = 0, end =0;

        int value = n.intValue();

        if(value <0){
            start = value;
        } else {
            end = value;
        }

        BigInteger[] bigIntegers = new BigInteger[3];

        for (; start <= end; start++) {
            int offset = start - value;
            if(offset==0 || offset==1){
                bigIntegers[offset] = BigInteger.valueOf(offset);
                continue;
            }
            bigIntegers[2] = bigIntegers[0].add(bigIntegers[1]);
            bigIntegers[0] = bigIntegers[1];
            bigIntegers[1] = bigIntegers[2];
        }

        return bigIntegers[2];
    }

    @Test
    public void testFib0() {
        testFib(0, 0);
    }

    @Test
    public void testFib1() {
        testFib(1, 1);
    }

    @Test
    public void testFib2() {
        testFib(1, 2);
    }

    @Test
    public void testFib3() {
        testFib(2, 3);
    }

    @Test
    public void testFib4() {
        testFib(3, 4);
    }

    @Test
    public void testFib5() {
        testFib(5, 5);
    }

    private static void testFib(long expected, long input) {
        BigInteger found;
        try {
            found = Fibonacci.fib(BigInteger.valueOf(input));
        }
        catch (Throwable e) {
            // see https://github.com/Codewars/codewars.com/issues/21
            throw new AssertionError("exception during test: "+e, e);
        }
        assertEquals(BigInteger.valueOf(expected), found);
    }

}
