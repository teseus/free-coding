package freecoding.exercise.hackersrank.appleandorange;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    // Complete the countApplesAndOranges function below.
    static void countApplesAndOranges(int s, int t, int a, int b, int[] apples, int[] oranges) {

        validateDistance(apples);
        validateDistance(oranges);
        validatePosition(s, t, a, b);

        long apple = Arrays.stream(apples)
                .filter(it -> isInside(s, t, a, it))
                .count();

        long orange = Arrays.stream(oranges)
                .filter(it->isInside(s, t, b, it))
                .count();

        System.out.println(apple);
        System.out.println(orange);

    }

    private static void validatePosition(int s, int t, int a, int b) {
        if (a >= s || s >= t || t >= b) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateDistance(int[] distances) {
        for (int distance : distances) {
            if (-100000 > distance || distance > 100000) {
                throw new IllegalArgumentException();
            }
        }
    }

    private static boolean isInside(int left, int right, int root, int relativeLocation){

        int absLocation = root + relativeLocation;

        if(left<=absLocation&&absLocation<=right) {
            return true;
        }
        return false;
    }

    private static int rangeValidate(int value){
        if(1<=value&&value<=100000){
            return value;
        }
        throw new IllegalArgumentException();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] st = scanner.nextLine().split(" ");

        int s = rangeValidate(Integer.parseInt(st[0]));

        int t = rangeValidate(Integer.parseInt(st[1]));

        String[] ab = scanner.nextLine().split(" ");

        int a = rangeValidate(Integer.parseInt(ab[0]));

        int b = rangeValidate(Integer.parseInt(ab[1]));

        String[] mn = scanner.nextLine().split(" ");

        int m = rangeValidate(Integer.parseInt(mn[0]));

        int n = rangeValidate(Integer.parseInt(mn[1]));

        int[] apples = new int[m];

        String[] applesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int applesItem = Integer.parseInt(applesItems[i]);
            apples[i] = applesItem;
        }

        int[] oranges = new int[n];

        String[] orangesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int orangesItem = Integer.parseInt(orangesItems[i]);
            oranges[i] = orangesItem;
        }

        countApplesAndOranges(s, t, a, b, apples, oranges);

        scanner.close();
    }
}
