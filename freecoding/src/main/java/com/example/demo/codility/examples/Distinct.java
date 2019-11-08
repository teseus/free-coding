package com.example.demo.codility.examples;

// note: remember to import (for using "Arrays.sort(xxx[])")

import java.util.Arrays;

// System.out.println("this is a debug message");

class Distinct {
    public int solution(int[] A) {

      // special case
        if(A.length ==0)
            return 0;
      
      // initial setting: one distinct number
        int result =1;
        
      // Using "Arrays.sort(A)" (important)
        Arrays.sort(A);
        
      // for counting the distinct numbers
        for(int i=1; i < A.length; i++){
            if(A[i] != A[i-1]){ // distinct
                result++; 
            }
        }
        
        return result; // return the number of distinct values
    }
}
