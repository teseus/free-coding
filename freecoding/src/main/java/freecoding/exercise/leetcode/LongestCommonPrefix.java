package freecoding.exercise.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public String longestCommonPrefix1(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        int minLen = getMinLen(strs);

        if(minLen == 0) {
            return "";
        }

        int count, j;
        String common = "";
        for (count = 1; count <= minLen; count++) {
            common = strs[0].substring(0, count);
            for (j = 1; j < strs.length; j++) {
                if(!common.equals(strs[j].substring(0,count))){
                    break;
                }
            }
            if(j != strs.length){
                if(common.length()>0) {
                    common = strs[0].substring(0, count - 1);
                }
                break;
            }
        }

        return common;
    }

    private int getMinLen(String[] strs) {
        int minLen = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
             minLen = Math.min(minLen,strs[i].length());
        }
        return minLen;
    }

    @Test
    public void test1(){
        assertEquals("fl", longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        assertEquals("flower", longestCommonPrefix(new String[]{"flower", "flower", "flower"}));
        assertEquals("", longestCommonPrefix(new String[]{"1flower", "2flow", "3flight"}));
        assertEquals("", longestCommonPrefix(new String[]{"", "", ""}));
        assertEquals("", longestCommonPrefix(new String[]{}));
    }

    @Test
    public void temp(){
        String str = "0123456789";
        assertEquals(4, str.indexOf("456"));
        assertEquals(-1, str.indexOf("456a"));
    }
}

