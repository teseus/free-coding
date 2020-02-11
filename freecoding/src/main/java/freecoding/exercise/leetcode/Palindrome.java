package freecoding.exercise.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class Palindrome {
    public static boolean isPalindrome(int x) {
        if(0>x) return false;

        int val = x;
        int rev = 0;
        while(val>0){
            rev = rev * 10 + val%10;
            val /= 10;
        }

        return rev == x;
    }

    public static boolean isPalindrome1(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber/10;
    }

    @Test
    public void test1(){
        assertTrue(Palindrome.isPalindrome1(121));
        assertFalse(Palindrome.isPalindrome1(-121));
        assertTrue(Palindrome.isPalindrome1(1221));
        assertTrue(Palindrome.isPalindrome1(12021));
        assertTrue(Palindrome.isPalindrome1(123454321));
        assertTrue(Palindrome.isPalindrome1(1234554321));
    }
}
