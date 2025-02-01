package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> fruitCounts = new HashMap<>();
        fruitCounts.put("apple", 10);
        fruitCounts.put("orange", 20);
        fruitCounts.put("pear", 30);

        System.out.printf("Размер 'количество' map %d%n", fruitCounts.size());
        System.out.printf("Apple: %d%n", fruitCounts.getOrDefault("apple", 0));
        System.out.printf("Orange: %d%n", fruitCounts.getOrDefault("orange", 0));
        System.out.printf("Pear: %d%n", fruitCounts.getOrDefault("pear", 0));
        System.out.printf("Peper: %d%n", fruitCounts.getOrDefault("peper", -128));
    }
}
