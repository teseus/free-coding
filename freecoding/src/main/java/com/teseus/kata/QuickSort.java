package com.teseus.kata;

import java.util.Arrays;

public class QuickSort {
    public static int[] sort(int[] source) {
        int copied[] = new int[source.length];
        System.arraycopy(source,0,copied,0,source.length);
        System.out.println(Arrays.toString(source));
        quicksort(copied, 0, copied.length-1);

        return copied;
    }

    private static void quicksort(int[] arr, int start, int end) {
        int pivot = partitioning(arr, start, end);
        if(start<pivot-1){
            quicksort(arr, start, pivot-1);
        }
        if(pivot<end){
            quicksort(arr, pivot, end);
        }
    }

    private static int partitioning(int[] arr, int start, int end) {
        int s = start, e = end;
        printArrPart(arr, start, s, e);
        int medium = arr[(start+end)/2];
        System.out.println("first selected medium index: " + (start+end)/2 + ", medium value: " + medium);
        while(start<end){
            while(arr[start]<medium)start++;
            while(medium<arr[end])end--;
            if(start<=end)
                swap(arr, start++, end--);
        }
        printArrPart(arr, start, s, e);
        return start;
    }

    private static void printArrPart(int[] arr, int start, int s, int e) {
        System.out.print("[");
        for (int i = s; i <= e; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("], start Idx: " + start + ", start value: " + arr[start]);
    }

    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
