package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings and , return if can be constructed by using the letters from and otherwise.ransomNotemagazinetrueransomNotemagazinefalse
 * Each letter in can only be used once in .magazineransomNote
 * Example 1:
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 * Constraints:
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote and consist of lowercase English letters.magazine
 */
public class RansomNote {
    public static void main(String[] args) {
        RansomNote solution = new RansomNote();
//        System.out.println(solution.canConstruct("a", "b")); // Вывод: false
//        System.out.println(solution.canConstruct("aa", "ab")); // Вывод: false
        System.out.println(solution.canConstruct("aa", "aab")); // Вывод: true
    }
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magazineCounts = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            magazineCounts.put(c, magazineCounts.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            if (!magazineCounts.containsKey(c) || magazineCounts.get(c) <= 0) {
                return false;
            }
            magazineCounts.put(c, magazineCounts.get(c) - 1);
        }
        return true;
    }
}
