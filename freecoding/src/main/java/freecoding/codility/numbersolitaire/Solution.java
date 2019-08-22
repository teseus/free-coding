package freecoding.codility.numbersolitaire;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Solution {

    public int solution(int[] A) {

        // main idea:
        // using "dynamic programming" to build up the solution
        // (bottom up)
        log.info(Arrays.toString(A));
        int[] dp = new int[A.length];
        dp[0] = A[0];

        // build up from "dp[1], dp[2], ..., dp[A.length-1]"
        for(int i=1; i<A.length; i++){

            // keep the biggest one
            // be very careful: could be negtive (so use Integer.MIN_VALUE)
            int max = Integer.MIN_VALUE;

            // a die could be "1 to 6"
            for(int die=1; die<=6; die++){
                if( i-die >= 0){
                    // very important: not "A[i-die]+A[i]"
                    // instead, have to use "dp[i-die]+A[i]"
                    max = Math.max( dp[i-die]+A[i], max );
                    // dynamic programming:
                    // take the best:
                    // takeBest( dp[i-j] + value[j], curBest )
                }
            }

            dp[i] = max; // keep the best one as the dp value
            log.info("idx[{}], max[{}]", i, max);

        }

        return dp[A.length-1];
    }

    public int solution1(int[] A){
        log.info(Arrays.toString(A));
        int lastIdx = A.length-1;
        List<Integer> marks = new ArrayList<>();
        marks.add(A[0]);
        log.info("idx[{}],value[{}]", 0, A[0]);

        int temp = A[1];
        int last = A[1];
        int count = 1;

        for (int i = 1; i <= lastIdx; i++) {
            temp = Math.max(temp, A[i]);
            if (count == 6 || i == lastIdx) {
                log.info("cur count[{}], cur idx [{}], max[{}]", count, i, temp);
                count = 1;
                last = temp;
                temp=-10000;
                marks.add(last);
            } else {
                count++;
            }
        }

        if(A[lastIdx] != last){
            marks.add(A[lastIdx]);
            log.info("idx[{}],value[{}]", lastIdx, A[lastIdx]);
        }


        return marks.stream()
                .mapToInt(it->it)
                .sum();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int [] arr;
        arr = new int[]{1, -2, 0, 9, -1, -2};
        log.info("the answer : {}\n", solution.solution(arr));

        arr = new int []{-4, -10, -7};
        log.info("the answer : {}\n", solution.solution(arr));

        arr = new int []{0, -4, -5, -2, -7, -9, -3, -10};
        log.info("the answer : {}\n", solution.solution(arr));

        arr = new int []{1, -2, 4, 3, -1, -3, -7, 4, -9};
        log.info("the answer : {}\n", solution.solution(arr));

//        arr = new int []{1, 6,2,3,4,5, 7};
//        log.info("the answer : {}\n", solution.solution(arr));
//
//        arr = new int []{1, 2,3,7,4,5, 6};
//        log.info("the answer : {}\n", solution.solution(arr));
//
//        arr = new int []{1, 2,3,4,5,6,7, 8,9};
//        log.info("the answer : {}\n", solution.solution(arr));
//
//        arr = new int []{1, 2,3,4,5,6,7, 8,9,10,11,12,13 };
//        log.info("the answer : {}\n", solution.solution(arr));
//
//        arr = new int []{1, 2};
//        log.info("the answer : {}\n", solution.solution(arr));

    }
}
