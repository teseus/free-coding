package com.example.teseus.freecoding.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MeanMediaMode {

    public static void main(String[] args) throws IOException {
        try(BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in))){
            while(true) {
                String inputValue = buffReader.readLine();
                if (inputValue.equals("quit")) {
                    break;
                }
            }
        }
        System.out.println("bye bye");
    }

    public static double getAverageValue(int [] numbers){
        return Arrays.stream(numbers)
                .average().orElse(0.0f);
    }

    public static double getAverageVal(int [] numbers){
        int accumulator = 0;
        for (int i = 0; i < numbers.length; i++) {
            accumulator += numbers[i];
        }
        return (double)accumulator / numbers.length;
    }

    public static int getMeanVal(int [] numbers) {
        int[] copiedNumbers = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(copiedNumbers);
        double meanIdx = Math.floor(numbers.length/2);
        return copiedNumbers[(int)meanIdx];
    }
}
