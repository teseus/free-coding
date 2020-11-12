package freecoding.exercise.hackersrank.array.ds;

import static org.junit.Assert.assertEquals;

public class Solution {
    static int hourglassSum(int[][] arr) {
        int [][] index = {{0,0},{0,1},{0,2},{1,1},{2,0},{2,1},{2,2}};
        int [][] sums = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        int max = -64;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < index.length; k++) {
                    int y = index[k][0];
                    int x = index[k][1];
                    sums[i][j] += arr[y+i][x+j];
                }
                max = Math.max(sums[i][j], max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        assertEquals(28,
                Solution.hourglassSum(new int[][]{
                        {-9, -9, -9, 1, 1, 1},
                        {0, -9, 0, 4, 3, 2,},
                        {-9, -9, -9, 1, 2, 3},
                        {0, 0, 8, 6, 6, 0},
                        {0, 0, 0, -2, 0, 0},
                        {0, 0, 1, 2, 4, 0}
                }));
    }
}
