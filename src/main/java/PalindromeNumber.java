/**
 * Given an integer , return if is a xtruex
 * palindrome
 * , and otherwise.false
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * Example 2:
 * <p>
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 * <p>
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= x <= 231 - 1
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        int value = 121;
        boolean isPall = isPalindrome(value);
        if (isPall) {
            System.out.printf("Is palindrome :%s%n", true);
        } else {
            System.out.printf("Is not palindrome :%s%n", false);
        }
        int unit =  10 * 2 + 2;
        System.out.println(unit);
    }

    public static boolean isPalindrome(int x) {
        int org = x;                // 121            || 12                || 1
        int rev = 0;                // 0              || 1
        while (org > 0) {
            int unit = org % 10;   // 121 % 10 = 1    || 12 % 10 = 2       || 1
            rev = 10 * rev + unit; // 10 * 0 + 1 = 1  || 10 * 1 + 2 = 2    || 10 * 12  + 1 = 121
            org /= 10;             // 121 / 10 = 12   || 121 / 10 = 12     || 1
        }

        return rev == x;
    }
}
