package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * Даны массив целых чисел и целое число , возвращают индексы двух чисел так, что их сумма равна целевому значению.numstarget
 * Можно предположить, что каждый вход будет иметь ровно одно решение, и нельзя использовать один и тот же элемент дважды.
 * Вы можете вернуть ответ в любом порядке.
 * Пример 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Пример 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Пример 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSumV3(nums, target);
        System.out.println(Arrays.toString(result));
    }


    private static int[] twoSumV1(int[] nums, int target) {
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (target == nums[i] + nums[i + 1]) {
//                return new int[]{i, i + 1};
//            }
//        }
//        throw new IllegalArgumentException("No two sum solution");
        for (int i: nums) {
            if (i == target) {
                return new int[] {i, i};
            }
        }
        return null;
    }

    public static int[] twoSumV3(int[] nums, int target) {
        HashMap<Integer, Integer> prevMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int diff = target - num;
            if (prevMap.containsKey(diff)) {
                return new int[]{prevMap.get(diff), i};
            }
            prevMap.put(num, i);
        }
        return new int[]{};
    }

    private static int[] twoSumV2(int[] nums, int target) {
        return IntStream.range(0, nums.length - 1)
                .filter(i -> target == nums[i] + nums[i + 1])
                .mapToObj(i -> new int[]{i, i + 1})
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No two sum solution"));
    }

    private static int twoSumV5(int[] nums, int target) {
        return IntStream.range(0, nums.length - 1)
                .filter(i -> target == nums[i])
                .findFirst()
                .orElse(-1);
    }
    
}

