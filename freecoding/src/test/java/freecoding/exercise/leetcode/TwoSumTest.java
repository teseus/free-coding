package freecoding.exercise.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class TwoSumTest {
    @Test
    public void testTwoSum1(){
        //given
        int [] arr = {1,2,3,4,5,6,7,8,9};
        int want = 5;
        //when
        int [] ret = TwoSum.find(arr, want);
        //then
        assertEquals(ret[0] + ret[1], want);
    }
}