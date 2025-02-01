package org.example;

import java.util.HashMap;
import java.util.Map;

public class RemoveDuplicatesString {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 4, 5, 6, 7, 7, 7, 7};
        test1(array);
        String input = "Привет, Ккот!";
        String result = test2(input);
        System.out.println(result);
    }

    public static String test2(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        StringBuilder result = new StringBuilder();
        char previousChar = input.charAt(0);
        result.append(previousChar);
        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.toLowerCase(currentChar) != Character.toLowerCase(previousChar)) {
                result.append(currentChar);
                previousChar = currentChar;
            }
        }
        return result.toString();
    }

    public static void test1(int[] numbers) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : numbers) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " - " + entry.getValue() + " times");
            }
        }
    }
}

