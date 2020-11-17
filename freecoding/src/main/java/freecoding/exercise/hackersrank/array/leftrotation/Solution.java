package freecoding.exercise.hackersrank.array.leftrotation;

import static org.junit.Assert.assertArrayEquals;

public class Solution {
    static int[] rotLeft(int[] a, int d) {
        int[] ans = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            ans[(a.length+(i-d))%a.length] = a[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        assertArrayEquals(new int[]{5,1,2,3,4}, Solution.rotLeft(new int[]{1,2,3,4,5}, 4));
    }
}
