package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Duplication {
    public static void main(String[] args) {
        int[] array = {1, 2, 33, 2};

        Integer firstDuplicate = findFirstDuplicate(array);

        if (firstDuplicate != null) {
            System.out.println(firstDuplicate);
        } else {
            System.out.println("Дубликат отсутствует");
        }
    }


    public static Integer findFirstDuplicate(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int value : array) {
            if (set.contains(value)) {
                return value;
            }
            set.add(value);
        }
        return null;
    }
}