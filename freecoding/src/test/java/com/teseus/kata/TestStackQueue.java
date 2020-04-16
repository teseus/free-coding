package com.teseus.kata;

import org.junit.Test;

import java.util.Stack;

public class TestStackQueue {
    @Test
    public void testStack(){
        int[] arr = new int[]{1,2,3,4,5};
        Stack<Integer> integerStack = new Stack<Integer>();

        for (int i = 0; i < arr.length; i++) {
            integerStack.push(i);
        }

        while(integerStack.isEmpty()){
            System.out.println(integerStack.pop());
        }
    }
}
