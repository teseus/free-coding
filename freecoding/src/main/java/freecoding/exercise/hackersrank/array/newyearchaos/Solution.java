package freecoding.exercise.hackersrank.array.newyearchaos;

public class Solution {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int count = 0;
        int[] copy = new int[q.length];
        for (int i = 1; i <= q.length; i++) {
            copy[i-1] = i;
        }

        for (int i = copy.length; i > 0; i--) {
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


        System.out.println(count);
    }

    private static void swap(int[] copy, int i, int diff) {
        for (int j = 1; j <= diff; j++, i--) {
            int temp = copy[i];
            copy[i] = copy[i-j];
            copy[i-1] = temp;
        }
    }

    public static void main(String[] args) {
//        Solution.minimumBribes(new int[]{2,1,5,3,4});
//        Solution.minimumBribes(new int[]{5, 1, 2, 3, 7, 8, 6, 4});
        Solution.minimumBribes(new int[]{1, 2, 5, 3, 7, 8, 6, 4});
//        Solution.minimumBribes(new int[]{2,5,1,3,4});
    }
}
