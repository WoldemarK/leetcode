package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int[] target = new int[10];

        System.out.println(Arrays.toString(sum(arr, target)));
    }

    public static int[] sum(int[] arr, int[] target) {
        int i = 0;
        while (i < arr.length) {
            target[i] = arr[i++];

        }
        return target;
    }
}
