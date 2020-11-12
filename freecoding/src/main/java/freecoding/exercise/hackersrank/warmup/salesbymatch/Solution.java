package freecoding.exercise.hackersrank.warmup.salesbymatch;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class Solution {

    static int sockMerchant(int n, int[] ar) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if(set.contains(ar[i])){
                set.remove(ar[i]);
                count ++;
            } else set.add(ar[i]);
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        assertEquals(3, sockMerchant(9, new int[]{10, 20, 20, 10, 10, 30, 50, 10, 20}));
    }
}

