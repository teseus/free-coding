package freecoding.exercise.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinCostClimbingStairs {
    @Test
    public void testFunc(){
        //given:
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //when:
        int ret = getShortCut(arr);
        //then:
        assertEquals(ret, 12);
    }

    @Test
    public void testFunc1(){
        //given:
        int[] arr = {10, 15, 20};
        //when:
        int ret = getShortCut(arr);
        //then:
        assertEquals(ret, 15);
    }

    @Test
    public void testFunc2(){
        //given:
        int[] arr = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        //when:
        int ret = getShortCut(arr);
        //then:
        assertEquals(ret, 6);
    }

    private int getShortCut(int[] arr) {
        int step1=arr[arr.length-2], step2=arr[arr.length-1], current;
        for (int i = arr.length-3; i >= 0; i--) {
            current = Math.min(arr[i] + step1, arr[i] + step2);
            step2 = step1;
            step1 = current;
        }
        return Math.min(step1, step2);
    }
}
