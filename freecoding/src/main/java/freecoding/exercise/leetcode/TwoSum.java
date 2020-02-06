package freecoding.exercise.leetcode;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TwoSum {

    public static int[] find(int[] arr, int want) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int find = want - arr[i];
            if (map.containsKey(find)) {
                return new int[]{map.get(find), i};
            }
            map.put(arr[i], i);
        }

        throw new IllegalArgumentException("no answer");

    }

    @Test
    public void testTwoSum1(){
        //given
        int [] arr = {10,2,3,4,5,6,7,8,9};
        int want = 5;
        //when
        int [] ret = TwoSum.find(arr, want);
        //then
        assertEquals(arr[ret[0]] + arr[ret[1]], want);
    }

}
