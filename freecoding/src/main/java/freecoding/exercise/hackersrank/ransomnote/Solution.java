package freecoding.exercise.hackersrank.ransomnote;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> magazineMap = new HashMap<>();
        Arrays.stream(magazine)
                .forEach(it->magazineMap.put(it, magazineMap.getOrDefault(it, 0) + 1));

        for (String word : note) {
            if(magazineMap.containsKey(word) && magazineMap.get(word) > 0)
                magazineMap.put(word, magazineMap.get(word) - 1);
            else {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }

    public static void main(String[] args) {
        checkMagazine("ive got a lovely bunch of coconuts".split(" "), "ive got some coconuts".split(" "));
        checkMagazine("two times three is not four".split(" "), "two times two is four".split(" "));
        checkMagazine("give me one grand today night".split(" "), "give one grand today".split(" "));
    }
}
