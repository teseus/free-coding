package freecoding.exercise.hackersrank.warmup.jumpingontheclouds;

import static org.junit.Assert.assertEquals;

public class Solution {

    static int jumpingOnClouds(int[] c) {
        int start = 0;
        int jump = 0;
        for (int i = 0; i < c.length; i++) {
            if(c[i] == 1){
               jump += ((i-start)/2) + 1;
               start = ++i;
            } else if (i == c.length-1) {
                jump = (int) (jump + Math.ceil((i - start) * 1.0f / 2.0f));
            }
        }
        System.out.println(jump);
        return jump;
    }

    public static void main(String[] args) {
        assertEquals(4, jumpingOnClouds(new int[]{0,0,1,0,0,1,0}));
        assertEquals(3, jumpingOnClouds(new int[]{0,0,0,0,1,0}));
        assertEquals(2, jumpingOnClouds(new int[]{0,1,0,0}));
        assertEquals(2, jumpingOnClouds(new int[]{0,1,0,0,0}));
        assertEquals(3, jumpingOnClouds(new int[]{0,1,0,0,0,0}));
    }
}

