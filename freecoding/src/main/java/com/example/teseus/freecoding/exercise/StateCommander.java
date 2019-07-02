package com.example.teseus.freecoding.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class StateCommander {

    private static Map<CurrentMode, Function> fucntionDistributeMap = new HashMap<>();

    private static CurrentMode currentMode;

    public static void registerFunction(CurrentMode mode, Function function){
        fucntionDistributeMap.put(mode, function);
    }

    public static Object execute(Integer input) {
        return fucntionDistributeMap.get(currentMode).apply(input);
    }

    public static CurrentMode swith(CurrentMode mode) {
        return currentMode = mode;
    }
}
