package com.example.teseus.freecoding.exercise;

import java.util.Scanner;

public class Triplets {

    public static void main(String[] args) {
        String alice[] = new String[3], bob[] = new String[3];

        try(Scanner scan = new Scanner(System.in)) {

            System.out.println("please input for Alice's score.");
            for (int i = 0; i < 3 ; i++) {
                alice[i] = scan.next();
            }

            System.out.println("please input for Blice's score.");
            for (int i = 0; i < 3 ; i++) {
                bob[i] = scan.next();
            }

        }

        //System.out.println("you input = " + List.of(alice) + "," + List.of(bob));

    }


}
