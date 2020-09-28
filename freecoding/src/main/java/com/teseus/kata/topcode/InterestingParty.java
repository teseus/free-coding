package com.teseus.kata.topcode;

import org.junit.Assert;

import java.util.Comparator;
import java.util.HashMap;

public class InterestingParty {
    public static int bestInvitaion(String[] first, String[] second){
        HashMap<String, Integer> map = new HashMap<>();

        putToMap(first, map);
        putToMap(second, map);

        return map.values().stream()
                .max(Comparator.comparing(it->it))
                .orElseThrow(IllegalStateException::new);
    }

    private static void putToMap(String[] first, HashMap<String, Integer> map) {
        for (String s : first) {
            if(map.containsKey(s)){
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
    }

    public static void main(String[] args) {
        Assert.assertEquals(4, bestInvitaion(
                new String[]{"fishing","gardening","swimming", "fishing"},
                new String[]{"hunting","fishing","fishing","biting"}));
        Assert.assertEquals(1, bestInvitaion(
                new String[]{"variety","diversity","loquacity", "courtesy"},
                new String[]{"talking","speaking","discussion","meeting"}));
        Assert.assertEquals(3, bestInvitaion(
                new String[]{"snakes","programming","cobra", "monty"},
                new String[]{"python","python","anaconda","python"}));
    }
}
