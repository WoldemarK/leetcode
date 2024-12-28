package org.example;

import java.text.MessageFormat;
import java.util.Arrays;

public class RevertArrays {
    public static void main(String[] args) {
        int[] my_array1 = {
                1789, 2035, 1899, 1456, 2013,
                1458, 2458, 1254, 1472, 2365,
                1456, 2165, 1457, 2456};
        System.out.println(MessageFormat.format("До преобразования {0}", Arrays.toString(my_array1)));
        int[] result = revert(my_array1);
        System.out.println(MessageFormat.format("После преобразования {0}", Arrays.toString(result)));
    }

    public static int[] revert(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        return arr;
    }
}
