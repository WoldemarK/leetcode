package org.example;

import java.util.Arrays;

/**
 * Given two strings s and t, return true if t is an
 * anagram
 * of s, and false otherwise.
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 * Constraints:
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 */
public class ValidAnagram {
    public static void main(String[] args) {
        String s1 = "anagram", t1 = "nagaram";
        String s2 = "rat", t2 = "car";

        System.out.println(isAnagram(s1, t1));  // Результат: true
        System.out.println(isAnagram(s2, t2));
    }
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()){
            return false;
        }
        char[] sArray = s1.toCharArray();
        char[] tArray = s2.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);
        return Arrays.equals(sArray, tArray);
    }
}
