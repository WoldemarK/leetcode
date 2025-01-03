package org.example;

import java.text.MessageFormat;
import java.util.Arrays;

public class RevertArrays {
    public static void main(String[] args) {
        int[] my_array1 = {
                1789, 2035, 1899, 1456, 2013};
        System.out.println(MessageFormat.format("До преобразования {0}", Arrays.toString(my_array1)));
        int[] result = revert(my_array1);
        System.out.println(MessageFormat.format("После преобразования {0}", Arrays.toString(result)));
    }

    public static int[] revert(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];

            int war1 = arr[arr.length - 1 - i];
            System.out.println(war1);

            arr[arr.length - 1 - i] = temp;
            int war2 = arr[arr.length - 1 - i];
            System.out.println(war2);
        }
        return arr;
    }
}
