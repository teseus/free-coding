package freecoding.exercise.hackersrank.array.newyearchaos;

import java.util.Arrays;

public class Solution {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int count = 0;
        int[] copy = new int[q.length];
        for (int i = 1; i <= q.length; i++) {
            copy[i-1] = i;
        }

        for (int i = 1; i <= copy.length; i++) {
            int diff = q[i-1] - i;
            if(diff > 2) {
                System.out.println("Too chaotic");
                return;
            }
            if (diff > 0) {
                count += diff;
                swap(copy, i, diff);
            }
        }

        System.out.println(Arrays.toString(copy));
        System.out.println(count);
    }

    private static void swap(int[] copy, int i, int diff) {
        int temp = copy[i];
        for (int j = i-diff; j>0; j--) {
            copy[j+1] = copy[j];
        }
        copy[i-diff] = temp;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        Solution.swap(array, 2, 2);
        System.out.println(Arrays.toString(array));

//        Solution.minimumBribes(new int[]{2,1,5,3,4});
//        Solution.minimumBribes(new int[]{5, 1, 2, 3, 7, 8, 6, 4});
//        Solution.minimumBribes(new int[]{1, 2, 5, 3, 7, 8, 6, 4});
//        Solution.minimumBribes(new int[]{2,5,1,3,4});
    }
}
