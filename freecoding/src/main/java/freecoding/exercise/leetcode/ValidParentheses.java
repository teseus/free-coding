package freecoding.exercise.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

public class ValidParentheses {
    @Test
    public void test1(){
        Solution s = new Solution();
        assertTrue(s.isValid("()"));
        assertTrue(s.isValid("()[]{}"));
        assertTrue(!s.isValid("(]"));
        assertTrue(!s.isValid("([)]"));
        assertTrue(s.isValid("{[]}"));
    }

    class Solution {

        // Hash table that takes care of the mappings.
        private HashMap<Character, Character> mappings;

        // Initialize hash map with mappings. This simply makes the code easier to read.
        public Solution() {
            this.mappings = new HashMap<Character, Character>();
            this.mappings.put(')', '(');
            this.mappings.put('}', '{');
            this.mappings.put(']', '[');
        }

        public boolean isValid(String s) {

            // Initialize a stack to be used in the algorithm.
            Stack<Character> stack = new Stack<Character>();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                // If the current character is a closing bracket.
                if (this.mappings.containsKey(c)) {

                    // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                    char topElement = stack.empty() ? '#' : stack.pop();

                    // If the mapping for this bracket doesn't match the stack's top element, return false.
                    if (topElement != this.mappings.get(c)) {
                        return false;
                    }
                } else {
                    // If it was an opening bracket, push to the stack.
                    stack.push(c);
                }
            }

            // If the stack still contains elements, then it is an invalid expression.
            return stack.isEmpty();
        }
    }

class SolutionMine {
    public boolean isValid(String s) {
        if(s.isBlank()) return true;

        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        char aChar;
        for (int i = 0; i < chars.length; i++) {
            aChar = chars[i];
            if(!stack.empty() && getKey(stack.peek()).equals(getKey(aChar))){
                if(getVal(aChar) < 0 ){
                    stack.pop();
                } else {
                    stack.push(aChar);
                }
            } else { //new
                if(getVal(aChar) == -1) return false;
                stack.push(aChar);
            }
        }

        return stack.empty();
    }

    String getKey(char c){
        switch (c) {
            case '(' :
            case ')' :
                return "1";
            case '{' :
            case '}' :
                return "2";
            case '[' :
            case ']' :
                return "3";
            default :
                throw new IllegalStateException();
        }
    }

    int getVal(char c){
        switch (c) {
            case '(' :
            case '{' :
            case '[' :
                return 1;
            case ')' :
            case '}' :
            case ']' :
                return -1;
            default :
                throw new IllegalStateException();
        }
    }
}
}
