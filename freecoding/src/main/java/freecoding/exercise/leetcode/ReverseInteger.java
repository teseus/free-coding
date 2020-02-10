package freecoding.exercise.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReverseInteger {
    public static int reverse(int x) {
        int temp = x;
        long reverse = 0;
        List<Integer> store = new ArrayList<>();

        while (temp != 0){
            store.add(temp%10);
            temp /= 10;
        }

        long decimal = 1;
        for (int i = store.size()-1; i >= 0; i--) {
            reverse += store.get(i) * decimal;
            if(Integer.MIN_VALUE > reverse || Integer.MAX_VALUE < reverse)
                return 0;
            decimal *= 10;
        }


        return (int) reverse;
    }

    public int reverseLeetCode(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    @Test
    public void test1(){
        int reverse = ReverseInteger.reverse(123);
        assertEquals(321, reverse);
    }

    @Test
    public void test2(){
        int reverse = ReverseInteger.reverse(-123);
        assertEquals(-321, reverse);
    }

    @Test
    public void test3(){
        int reverse = ReverseInteger.reverse(120);
        assertEquals(21, reverse);
    }

    @Test
    public void test4(){
        int reverse = ReverseInteger.reverse(1534236469);
        assertEquals(0, reverse);
    }
}

