package freecoding.exercise.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RomanToInteger {
    static char[] map = new char[Character.MAX_CODE_POINT];
    static{
        map['I'] = 1;
        map['V'] = 5;
        map['X'] = 10;
        map['L'] = 50;
        map['C'] = 100;
        map['D'] = 500;
        map['M'] = 1000;
    }

    public static int romanToInt(String s) {
        int acc = 0, i, a, b;
        char[] split = s.toCharArray();
        for (i = 0; i < split.length-1; i++) {
            a = map[split[i]];
            b = map[split[i+1]];
            acc += (a>=b?a:-a);
        }
        acc += map[split[i]];
        return acc;
    }

    @Test
    public void test1(){
        assertEquals(10, RomanToInteger.romanToInt("X"));
        assertEquals(3, RomanToInteger.romanToInt("III"));
        assertEquals(8, RomanToInteger.romanToInt("VIII"));
        assertEquals(4, RomanToInteger.romanToInt("IV"));
        assertEquals(9, RomanToInteger.romanToInt("IX"));
        assertEquals(58, RomanToInteger.romanToInt("LVIII"));
        assertEquals(1994, RomanToInteger.romanToInt("MCMXCIV"));
    }
}
