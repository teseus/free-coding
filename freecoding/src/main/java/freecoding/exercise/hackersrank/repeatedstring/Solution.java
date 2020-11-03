package freecoding.exercise.hackersrank.repeatedstring;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Solution {

    static long repeatedString(String s, long n) {
        List<Integer> pos = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'a') pos.add(i);
        }

        long insize = n % s.length();
        long count = 0;

        for (int i = 0; i < pos.size(); i++) {
            if(pos.get(i) < insize) count++;
        }

        count += (n/s.length()) * pos.size();

        return count;
    }

    public static void main(String[] args) {
        assertEquals(7, repeatedString("aba", 10));
        assertEquals(1000000000000L, repeatedString("a", 1000000000000L));
    }
}
