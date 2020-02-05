package com.teseus.kata;

public class QuickSort {
    public static int[] sort(int[] source) {
        int copied[] = new int[source.length];
        System.arraycopy(source,0,copied,0,source.length);
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
        int medium = arr[(start+end)/2];
        System.out.println("partitioning start i: " + (start+end)/2 + ", v: " + medium);
        printSwapArr("<", arr, start, end, s, e);
        while(start<end){
            while(arr[start]<medium)start++;
            while(medium<arr[end])end--;
            if(start<=end) {
                printSwapArr("-", arr, start, end, s, e);
                swap(arr, start, end);
                printSwapArr("-", arr, start, end, s, e);
                start++; end--;
            }
        }
        printSwapArr(">", arr, start, end, s, e);
        return start;
    }

    private static void printSwapArr(String s1, int[] arr, int start, int end, int s, int e) {
        System.out.print(s1 + "[");
        for (int i = s; i <= e; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println("]"
                + ", si: " + start + ", sv: " + arr[start]
                + ", ei: " + end + ", ev: " + arr[end]);
    }

    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
