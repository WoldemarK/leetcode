package org.example;

public class Max_Min_Value_Arrays {

    public static void main(String[] args) {
        int[] my_array = {25, 14, 56, 15, 36, 56, 77, 18, 29, 49};
        max_min(my_array);
    }

    public static void max_min(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("Max value " + max);
        System.out.println("Min value " + min);
    }
}
