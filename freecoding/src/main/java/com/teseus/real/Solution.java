package com.teseus.real;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
    private int row;
    private int col;
    private int trackCode = 100;
    private int [][] matrix = null;

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int numberAmazonTreasureTrucks(int rows, int column,
                                   List<List<Integer> > grid)
    {
        // WRITE YOUR CODE HERE
        this.row = row;
        this.col = col;
        int[][] matrix = new int[row][col];
        return 0;
    }
    // METHOD SIGNATURE ENDS

    private int get(List<List<Integer>> a, int r, int c) {
        return a.get(r).get(c);
    }

    private boolean test(List<List<Integer>> a, int x, int y) {
        if(get(a, x, y) == 0) return false;
        trackCode ++;
        for (int i = x; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(get(a, i, j) != 1){
                    set(a, i, j, trackCode);
                    return true;
                }
            }
        }
        return false;
    }

    private void set(List<List<Integer>> a, int row, int col, int trackCode) {
        Integer integer = a.get(row).get(col);
    }

    private void check(List<List<Integer>> a, int trackCode, int row, int col){
        if(get(a, row, col) == 1) {
            check(a, trackCode, row, col + 1);
            check(a, trackCode, row + 1, col);
            check(a, trackCode, row - 1, col);
        }
    }

    @Test
    public void test1(){
        List<List<Integer>> a = new ArrayList<>() ;
        a.add(Arrays.asList(1,1,0,0));
        a.add(Arrays.asList(0,0,0,0));
        a.add(Arrays.asList(0,0,1,1));
        a.add(Arrays.asList(0,0,0,0));

        Solution s = new Solution();

        s.numberAmazonTreasureTrucks(4,4, a);

        assertEquals(s.get(a,0,0), 1);
        assertEquals(s.get(a,0,1), 1);
        assertEquals(s.get(a,1,1), 0);
        assertEquals(s.get(a,2,2), 1);
        assertEquals(s.get(a,2,3), 1);
        assertEquals(s.get(a,3,3), 0);

//        assertEquals(1, new Solution().numberAmazonTreasureTrucks(5,4,
//                Arrays.asList(new int[]{1})));
    }
}



