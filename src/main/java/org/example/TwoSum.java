package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Даны массив целых чисел и целое число , возвращают индексы двух чисел так, что их сумма равна целевому значению.numstarget
 * <p>
 * Можно предположить, что каждый вход будет иметь ровно одно решение, и нельзя использовать один и тот же элемент дважды.
 * <p>
 * Вы можете вернуть ответ в любом порядке.
 * <p>
 * <p>
 * <p>
 * Пример 1:
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Пример 2:
 * <p>
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Пример 3:
 * <p>
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSumV1(nums, target);
        System.out.println(Arrays.toString(result));
    }


    private static int[] twoSumV1(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (target == nums[i] + nums[i + 1]) {
                return new int[]{i, i + 1};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    private static int[] twoSumV2(int[] nums, int target) {
        return IntStream.range(0, nums.length - 1)
                .filter(i -> target == nums[i] + nums[i + 1])
                .mapToObj(i -> new int[]{i, i + 1})
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No two sum solution"));
    }
}
