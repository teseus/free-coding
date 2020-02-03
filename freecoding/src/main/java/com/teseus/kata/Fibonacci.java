package com.teseus.kata;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
//https://www.codewars.com/kata/53d40c1e2f13e331fc000c26/train/java
public class Fibonacci {

    public static BigInteger fib(BigInteger n) {
        int inputValue = n.intValue();

        if(n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)){
            return n;
        }

        if(inputValue > 20000){
            throw new IllegalArgumentException("out of index");
        }
        BigInteger[] bigIntegers = new BigInteger[inputValue+1];

        for (int i = 0; i <= inputValue; i++) {
            if(i==0 || i==1){
                bigIntegers[i] = BigInteger.valueOf(i);
                continue;
            }
            bigIntegers[i] = bigIntegers[i-1].add(bigIntegers[i-2]);
        }

        return bigIntegers[inputValue];
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
