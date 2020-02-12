package com.teseus.kata;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class QuickSort {

    @Test
    public void test1(){
        int[] arr = {9,1,2,7,8,10,11,12};
        assertArrayEquals(new int[]{1, 2, 7, 8, 9, 10, 11, 12}, QuickSort.sort(arr));
    }

    @Test
    public void test2() {
        assertArrayEquals(QuickSort.sort(
                new int[]{1, 2, 6, 3, 4, 5}),
                new int[]{1, 2, 3, 4, 5, 6});
    }

    @Test
    public void test3() {
        assertArrayEquals(QuickSort.sort(
                new int[]{1,5,9,8,7,4,6,3,2}),
                new int[]{1,2,3,4,5,6,7,8,9});
    }

    @Test
    public void test4() {
        assertArrayEquals(QuickSort.sort(
                new int[]{20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0}),
                new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});
    }

    @Test
    public void test5() {
        assertArrayEquals(QuickSort.sort(
                new int[]{20,14,13,12,11,10,9,19,18,17,16,15,8,7,6,5,4,3,2,1,0}),
                new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});
    }

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
        System.out.println("start medium start i: " + (start+end)/2 + ", v: " + medium);
        printSwapArr("<<", arr, start, end, s, e);
        while(start<=end){
            while(arr[start]<medium)start++;
            while(medium<arr[end])end--;
            if(start<=end) {
                printSwapArr("<", arr, start, end, s, e);
                swap(arr, start, end);
                printSwapArr(">", arr, start, end, s, e);
                start++; end--;
                printSwapArr("=", arr, start, end, s, e);
            }
        }
        printSwapArr(">>", arr, start, end, s, e);
        return start;
    }

    private static void printSwapArr(String s1, int[] arr, int start, int end, int s, int e) {
        System.out.print(s1 + "[");
        for (int i = s; i <= e; i++) {
            System.out.print(arr[i] + ", ");
        }

        System.out.println("]"
                + ", index(" + start + ", " + end + ")"
                + ", value(" + (start<=(arr.length-1)?arr[start]:"") + ", " + (end>=0?arr[end]:"") + ")");
    }

    private static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
